package com.bz.threads;

public class HeavyWorkRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Doing heavy processing - START " + Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
			doDBProcessing();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doDBProcessing() throws InterruptedException {
		Thread.sleep(5000);
	}

}
