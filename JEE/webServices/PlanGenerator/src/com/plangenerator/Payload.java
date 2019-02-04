package com.plangenerator;

public class Payload {

	private double loanAmount;
	private double nominalRate;
	private int duration;
	private String startDate;

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double remainingOutstandingPrincipal) {
		this.loanAmount = remainingOutstandingPrincipal;
	}

	public double getNominalRate() {
		return nominalRate;
	}

	protected void setNominalRate(double nominalRate) {
		this.nominalRate = nominalRate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
