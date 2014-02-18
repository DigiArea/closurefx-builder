package com.digiarea.closurefx;

public class Timer {

	private String key;
	private long start;
	private long end;
	private double duration;

	public Timer(String key) {
		super();
		this.key = key;
	}

	public void start() {
		start = System.nanoTime();
	}

	public void stop() {
		end = System.nanoTime();
		duration = (double) (end - start) / 1000000000.0;
	}

	@Override
	public String toString() {
		return key + ": " + Double.toString(duration);
	}

}
