package com.amelorate.ofp;

import java.util.ArrayList;

public class Interpreter 
{
	public WordMap words = new WordMap(this);
	public stack stack = new stack();
	public MethodDecoder methodDecoder = new MethodDecoder();
	public String errorAction = "warn";
	
	public Interpreter()
	{
		words.addNative("exmethod", "exMethod");
	}
	
	/**
	 * Executes the entirety of the string.
	 * @param code
	 * The code you want interpreted.
	 * @throws Exception 
	 */
	public void executeLine(String code) throws Exception
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
				words.doWord(cerrentValue);
			}
			
			i++;	// No idea why I can't use f here.
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
				return "notavar";
			}
			return "number";
		}
	}
	
	public void error(Exception exeption) throws Exception
	{
		if (errorAction == "exeption")
			throw exeption;
		else if (errorAction == "error")
			System.out.println("[Error] " + exeption.toString());
	}
	
	public static void debugText(String text, String source)
	{
		boolean debugMode = true;
		if (debugMode == true)
			System.out.println("[Debug] [" + source + "] " + text);
	}
	
	private ArrayList<String> splitLine(String line)
	{
		ArrayList<String> split = new ArrayList<String>();
		ArrayList<Integer> spacePoses = new ArrayList<Integer>();
		spacePoses.add(0);
		boolean stringOrTable = false;
		boolean secondPartOrGreater = false;
		line = line + " ";
		
		debugText(line + " is the raw text to be split.", "splitLine");
		
		for (int i = 0; i < line.length(); i++)
		{
			if (line.charAt(i) == ' ' && stringOrTable == false)
			{
				String addingString = line.substring(spacePoses.get(spacePoses.size() - 1), i);
				if (secondPartOrGreater == true)
					addingString = addingString.substring(1, addingString.length());
				split.add(addingString);
				spacePoses.add(i);	// It then records the position of the space and moves on.
				secondPartOrGreater = true;
			}
			
			if (line.charAt(i) == '\"'  || line.charAt(i) == '{')
				if (stringOrTable == false)
					stringOrTable = true;		// I do this because I don't know how && works with ||
			
			if (line.charAt(i) == '\"'  || line.charAt(i) == '}')
				if (stringOrTable == true)
					stringOrTable = false;
		}
		
		for (int i = 0; i < split.size(); i++)
			debugText(split.get(i) + " final split output.", "splitLine");
		
		return split;
	}
	
	private boolean isVariable(String section)
	{
		String type = getVariableType(section);
		
		debugText(type + " Is the type of " + section, "isVariable");
		
		if (type == "string" || type == "word" || type == "table" || type == "number" || type == "null")
			return true;
		else
			return false;
	}
}
