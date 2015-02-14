package com.amelorate.ofp;

import java.util.HashMap;

public class Interpreter 
{
	public HashMap<String, word> words;
	public stack stack = new stack();
	
	
	public Interpreter()
	{
		String[] contentSetting = new String[0];
		contentSetting[0] = "native";
		words.put("exmethod", new word(contentSetting, "exmethod"));
	}
	
	public void executeLine(String code)
	{
		// I need to first split the line up based on spaces into an array, but keep strings and tables together.
		// Then I need to check if the current thing is a variable.
		// If it is then I need to push it to the stack.
		// Else I need to execute it as a word.
	}
}
