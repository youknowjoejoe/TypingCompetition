package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

public abstract class Builder<T> {
	
	private static Long currentKey = 0L;
	
	private static Long generateKey(){
		return currentKey++;
	}
	
	private String name;
	private String subtext;
	private Long key;
	
	public Builder(String name, String subtext){
		this.name = name;
		this.subtext = subtext;
		this.key = generateKey();
	}

	public abstract T build();
	
	public String getName(){
		return name;
	}
	public String getSubtext(){
		return subtext;
	}
	public Long getKey(){
		return key;
	}
}
