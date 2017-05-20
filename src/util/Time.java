package util;

public class Time {
	public static double getTime(){
		return System.nanoTime()/1_000_000_000.0;
	}
}
