package util;

public class Belt<T> {
	private int offset;
	private T[] array;
	
	public Belt(T[] array){
		this.array = array;
		this.offset = 0;
	}
	
	public T get(int index){
		return array[(index+offset)%array.length];
	}
	
	public void set(int index, T value){
		array[(index+offset)%array.length] = value;
	}
	
	public void rotate(int n){
		offset=(offset-n)%array.length;
		if(offset<0) offset +=array.length;
	}
	
	public int length(){
		return array.length;
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
