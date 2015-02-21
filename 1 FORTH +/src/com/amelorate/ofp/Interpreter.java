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
		words.put("exmethod", new word(contentSetting, "exMethod"));
	}
	
	/**
	 * Executes the entirety of the string.
	 * @param code
	 * The code you want interpreted.
	 */
	public void executeLine(String code)
	{
		ArrayList<String> codeSplit = splitLine(code);
		int i = 0;
		String cerrentValue;
		
		for (int f = 0; f < codeSplit.size(); f++);		// I have no idea why I wasn't using for loops sooner.
		{
			cerrentValue = codeSplit.get(i);
			
			if (isVariable(cerrentValue))
			{
				stack.addValue(cerrentValue);
			}
			
			else
			{
				executeWord(cerrentValue);
			}
			
			i++;	// No idea why I can't use f here.
		}
	}
	
	/**
	 * Executes the string as if it were a word. This does not do variables.
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
	
	/**
	 * Removes the quotes from the beginning and end of a string.
	 * @param value
	 * The string you want quotes removed from.
	 * @return
	 * The string with the quotes removed.
	 */
	public String removeQuotes(String value)
	{
		return value.substring(1, value.length()-1);	// for some reason it doesn't work with spaces inbetween the ), the -, and the other ).
	}
	
	/**
	 * Gets the type of a variable.
	 * @param variable
	 * The variable you want to check.
	 * @return
	 * Returns the type of the variable.
	 * Can be number, string, word, table, null, and notavar if it isn't a variable.
	 */
	public String getVariableType(String variable)
	{
		if (variable == null)	// I don't know why this is null. But it fixes an exception.
			return "notavar";
		else if (variable.startsWith("\""))
			return "string";
		else if (variable.startsWith("{"))
			return "table";
		else if (variable.startsWith("^"))
			return "word";
		else if (variable == "null")
			return "null";
		else
		{
			try
			{
				Integer.parseInt(variable);
			}
			catch (NumberFormatException e)
			{
				return "number";
			}
			return "notavar";
		}
	}
	
		line = line + " ";
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
			
			if (line.charAt(i) == '\"'  || line.charAt(i) == '{')
				if (stringOrTable == false)
					stringOrTable = true;		// I do this because I don't know how && works with ||
			
			if (line.charAt(i) == '\"'  || line.charAt(i) == '}')
				if (stringOrTable == true)
					stringOrTable = false;
		}
		
		for (int i = 0; i < split.size(); i++)
			System.out.println(split.get(i));
		
		return split;
	}
	
	private boolean isVariable(String section)
	{
		String type = getVariableType(section);
		
		if (type == "string" || type == "word" || type == "table" || type == "number" || type == "null")
			return true;
		else
			return false;
	}
}
