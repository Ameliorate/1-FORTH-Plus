package com.amelorate.ofp;

import java.util.HashMap;

public class WordMap 
{
	private HashMap<String, word> words = new HashMap<String, word>();
	private Interpreter interpreter;
	
	public WordMap(Interpreter interpreter)
	{
		this.interpreter = interpreter;
	}
	
	public word getWord(String name)
	{
		return words.get(name);
	}
	
	public void addWord(String name, String content)
	{
		Interpreter.debugText("New word '" + name + "'", "addWord");
		words.put(name, new word(content));
	}
	
	public void addNative(String name, String method)
	{
		Interpreter.debugText("New native word '" + name + "' with method '" + method, "addNative");
		words.put(name, new word("native", method));
	}
	
	public void removeWord(String name)
	{
		words.remove(name);
	}
	
	public void doWord(String name)
	{
		Interpreter.debugText("Doing " + name, "doWord");
		
		word doingWord = words.get(name);
		if (doingWord == null)
			System.out.println("[Error] '" + name + "' is not a valid word.");
		else
			doingWord.Execute(interpreter);
		
	}
}
