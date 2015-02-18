package com.amelorate.ofp;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter 
{
	public HashMap<String, word> words = new HashMap<String, word>();
	public stack stack = new stack();
	public MethodDecoder methodDecoder = new MethodDecoder();
	
	
	public Interpreter()
	{
		String[] contentSetting = new String[1];
		contentSetting[0] = "native";
		word newWord = new word(contentSetting, "exMethod");
		words.put("exmethod", newWord);
	}
	
	public void executeLine(String code)
	{
		String[] codeSplit = splitLine(code);
		int i = 0;
		
		for (int f = 0; f < codeSplit.length; f++);		// I have no idea why I wasn't using for loops sooner.
		{
			if (isVariable(codeSplit[i]))
			{
				stack.addValue(codeSplit[i]);
			}
			
			else
			{
				executeWord(codeSplit[i]);
			}
			
			i++;	// No idea why I can't use f here.
		}
	}
	
	/**
	 * Executes the string as if it were a word.
	 * @param word
	 * The word you want to execute.
	 */
	public void executeWord(String word)
	{
		try
		{
		words.get(word).Execute(this);
		}
		catch (NullPointerException e) 
		{
			System.out.println("[Error] Bad word: '" + word + "'");
		}
	}
	
	public String removeQuotes(String value)
	{
		return value.substring(1, value.length()-1);	// for some reason it doesn't work with spaces inbetween the ), the -, and the other ).
	}
	
	private String[] splitLine(String line)
	{
		ArrayList<String> split = new ArrayList<String>();
		ArrayList<Integer> spacePoses = new ArrayList<Integer>();
		spacePoses.add(0);
		boolean stringOrTable = false;
		
		System.out.println(line);
		
		for (int i = 0; i < line.length(); i++)
		{
			if (line.charAt(i) == ' ' && stringOrTable == false)
			{
				split.add(line.substring(spacePoses.get(spacePoses.size() - 1), i));	// Basically, it will split the string based on spaces, then add it to split.
				spacePoses.add(i);	// It then records the position of the space and moves on.
			}
			
			else if (line.charAt(i) == '\"'  || line.charAt(i) == '{')
				if (stringOrTable == false)
					stringOrTable = true;		// I do this because I don't know how && works with ||
			
			else if (line.charAt(i) == '\"'  || line.charAt(i) == '}')
				if (stringOrTable == true)
					stringOrTable = false;
		}
		
		for (int i = 0; i < split.size(); i++)
			System.out.println(split.get(i));
		
		return split.toArray(new String[1]);
	}
	
	private boolean isVariable(String section)
	{
		if (section.startsWith("\"") == true)
			return true;	// String
		
		else if (section.startsWith("{") == true)
			return true; // Table
		
		else if (section.startsWith("^") == true)
			return true; // Word
		
		else
		{
			boolean isInt = true;
			try
			{
				Integer.parseInt(section);
			}
			catch (NumberFormatException e)
			{
				isInt = false;
			}
			
			return isInt;	// If the above try catch block threw, then it isn't an int and can be safely assumed to be a word to be executed.
			// if it didn't, then it is an int.
		}
	}
}
