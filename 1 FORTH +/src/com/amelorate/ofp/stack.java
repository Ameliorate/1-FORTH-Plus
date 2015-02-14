package com.amelorate.ofp;

import java.util.ArrayList;

public class stack {
	// Valid types are number, string, word, and table
	
	private ArrayList<String> values = new ArrayList<String>();
	
	public void addValue(String value)
	{
		values.add(value);
	}
	
	public String getValue()
	{
		String gettingValue = values.get(values.size());
		values.remove(values.size());
		return gettingValue;
	}
	
}
