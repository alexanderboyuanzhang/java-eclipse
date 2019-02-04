package com.plangenerator.businesslogics;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.plangenerator.MyResponse;
import com.plangenerator.Payload;

public class Calculations {

	// instance variables
	private Payload payload;
	private String outputString = "";
	MyResponse myResponse = new MyResponse();

	// empty constructor
	public Calculations() {

	}

	// the constructor used to retrieve incoming data
	public Calculations(Payload payload) {
		this.payload = payload;
	}

	// this is the method used by LoansService.java
	public void calculatePlan() throws ParseException {
		while (payload.getDuration() > 0) {
			
			if (payload.getDuration() < 24)
				outputString = outputString + ",";
			
			// please don't switch orders
			String date = payload.getStartDate();
			double interest = calculateInterest(payload.getNominalRate(), payload.getLoanAmount());
			double annuity = myResponse.getANNUITY(); // <-------------------------------------------
			double principal = calculatePrincipal(interest, annuity);
			double initialLoanAmount = payload.getLoanAmount();
			double remainingOutstandingPrincipal = initialLoanAmount - principal;

			if (remainingOutstandingPrincipal < 0) {
				annuity = format2Places(annuity + remainingOutstandingPrincipal);
				principal = format2Places(principal + remainingOutstandingPrincipal);
				remainingOutstandingPrincipal =0;
			}

			myResponse.setBorrowerPaymentAmount(format2Places(annuity));
			myResponse.setInitialOutstandingPrincipal(format2Places(initialLoanAmount));
			myResponse.setDate(date);
			myResponse.setInterest(interest);
			myResponse.setPrincipal(principal);
			myResponse.setRemainingOutstandingPrincipal(format2Places(remainingOutstandingPrincipal));

			outputString = outputString + "{" + "\"borrowerPaymentAmount\": \"" + myResponse.getBorrowerPaymentAmount()
					+ "\"," + "\"date\": \"" + myResponse.getDate() + "\"," + "\"initialOutstandingPrincipal\": \""
					+ myResponse.getInitialOutstandingPrincipal() + "\"," + "\"interest\": \""
					+ myResponse.getInterest() + "\"," + "\"principal\": \"" + myResponse.getPrincipal() + "\","
					+ "\"remainingOutstandingPrincipal\": \"" + myResponse.getRemainingOutstandingPrincipal() + "\""
					+ "}";
			System.out.println(payload.getDuration());
			payload.setStartDate(increaseTheDate(date));
			payload.setLoanAmount(remainingOutstandingPrincipal);
			payload.setDuration(payload.getDuration() - 1);
		}
	}

	//increase the date by month
	private String increaseTheDate(String dateIn) throws ParseException {
		String dateOutString;
		String dateInString = dateIn;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateInString = dateInString.replace("T", " ");
		dateInString = dateInString.replace("Z", "");
		Date date = dateFormat.parse(dateInString);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		if (cal.get(Calendar.MONTH) == 12) {
			cal.set(GregorianCalendar.YEAR, cal.get(Calendar.YEAR) + 1);
			cal.set(GregorianCalendar.MONTH, 1);
		} else
			cal.set(GregorianCalendar.MONTH, cal.get(Calendar.MONTH) + 1);
		date = cal.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateOutString = simpleDateFormat.format(date).toString() + "T00:00:00Z";
		return dateOutString;
	}

	// principal calculation
	private double calculatePrincipal(double interest, double annuity) {
		double principal = annuity - interest;
		return format2Places(principal);
	}

	// interest calculation
	private double calculateInterest(double nominalRate, double loanAmount) {
		double interest = (nominalRate * 30 * loanAmount) / 36000;
		return format2Places(interest);
	}

	// the method used to 
	private double format2Places(double d) {
		DecimalFormat f2p = new DecimalFormat("0.00");
		return Double.valueOf(f2p.format(d));
	}
	
	// this toString method performs the Service output
	@Override
	public String toString() {
		return "{ [ " + outputString + " ] }";
	}
}
