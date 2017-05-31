package util;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * May 2017
 * 
 */

import java.util.function.Consumer;

public class Belt<T> {
	private int offset;
	private T[] array;
	
	public Belt(T[] array){
		this.array = array;
		this.offset = 0;
	}
	
	public T get(int index){
		if((index+offset)%array.length < 0) System.out.println(offset);
		return array[(index+offset)%array.length];
	}
	
	public void set(int index, T value){
		array[(index+offset)%array.length] = value;
	}
	
	public void rotate(int n){
		int temp = (offset-n)%array.length;
		if(temp<0) temp +=array.length;
		offset = temp;
	}
	
	public int length(){
		return array.length;
	}
	
	public void forEach(Consumer<? super T> c){
		for(int rep = 0; rep < array.length; rep++){
			c.accept(get(rep));
		}
	}
	
	@Override
	public String toString(){
		String temp = "[";
		for(int i = 0; i < array.length; i++){
			temp += (get(i));
			if(i < array.length-1) temp+= ",";
		}
		temp += "]";
		return temp;
	}
}
