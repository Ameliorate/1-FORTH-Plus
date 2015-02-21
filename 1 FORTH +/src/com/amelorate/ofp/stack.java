package com.amelorate.ofp;

import java.util.ArrayList;

public class stack {
	// Valid types are number, string, word, table, and null.
	// null is currently only existent if you try to take something from an empty stack.
	
	private ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * Adds a value to the stack.
	 * @param value
	 * The value you want to add.
	 * Valid types are number, string, word, table, and null.
	 */
	public void addValue(String value)
	{
		values.add(value);
		Interpreter.debugText(value + " to stack");
	}
	
	/**
	 * Gets a value from the top of the stack. Also removes it.
	 * @return
	 * Returns the value from the stack.
	 */
	public String getValue()
	{
		if (values.size() == 0)
			return "null";
		else
		{
			String gettingValue = values.get(values.size());
			values.remove(values.size());
			return gettingValue;
		}
	}
	
}
