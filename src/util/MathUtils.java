package util;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

public class MathUtils {
	public static int max(int...values){
		int max = values[0];
		for(int rep = 0; rep < values.length; rep++){
			max = Math.max(max, values[rep]);
		}
		return max;
	}
	
	public static int min(int...values){
		int min = values[0];
		for(int rep = 0; rep < values.length; rep++){
			min = Math.min(min, values[rep]);
		}
		return min;
	}
}
