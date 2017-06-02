package core;

/* 
 * Joseph Sullivan
 * APCSA per. 3B
 * June 2017
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OptionActionListener<T> implements ActionListener {
	private Map<Long,Builder<T>> map;
	private String name;
	private Builder<T> currentOption;
	
	public OptionActionListener(String name, Builder<T> defaultOption){
		this.name = name;
		map = new HashMap<Long,Builder<T>>();
		currentOption = defaultOption;
	}
	
	public void addOption(Builder<T> option){
		map.put(option.getKey(), option);
	}
	
	public void setCurrentOption(Long l){
		Builder<T> option = map.get(l);
		if(option != null) currentOption = option;
	}
	
	public String getName(){
		return name;
	}
	
	public Builder<T> getCurrentOption(){
		return currentOption;
	}
	
	public Collection<Builder<T>> get(){
		return map.values();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setCurrentOption(Long.parseLong(e.getActionCommand()));
	}
}
