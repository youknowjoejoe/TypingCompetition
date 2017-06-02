package util;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

public class Time {
	public static double getTime(){
		return System.nanoTime()/1_000_000_000.0;
	}
}
