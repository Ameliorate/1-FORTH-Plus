package com.amelorate.ofp;

import java.lang.reflect.InvocationTargetException;

public class MethodDecoder {
	
	/**
	 * Executes a built in method.
	 * @param method
	 * The method in question.
	 * @param interpreter
	 * The interpreter to use.
	 * @throws Exception 
	 */
	public void execute(String method, Interpreter interpreter) throws Exception
	{
		java.lang.reflect.Method method1 = null;
		try 
		{
		  method1 = this.getClass().getMethod(method);		// Gets the requested method.
		}
		catch (SecurityException e) {}
		catch (NoSuchMethodException e) {}
		
		try 
		{
			method1.invoke(method, interpreter);		// Invokes the method.
		}
		catch (IllegalArgumentException e) {}
		catch (IllegalAccessException e) {}
		catch (InvocationTargetException e) 
		{
			interpreter.error(new Exception("InvocationTargetException: This is likely caused by calling a nonexistant method."));
		}
		// Reflection made this a lot easier than it would be otherwise. In C# this would be many lines long.
		// 
	}
	
	// Perhaps I should do this in a different file?
	
	// This is the template method.
	@SuppressWarnings("unused")		// It warns you that it is not used otherwise, even though it is.
	private void helloWorld(Interpreter interpreter)	// It should be private to make the list uncluttered, and should take the interpreter as an argument even if it isn't used.
	{
		System.out.println("Hello World!");		// Do stuff here.
	}
	
	/**
	 * Exposes all other native methods to the program.
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private void exMethod(Interpreter interpreter) throws Exception
	{
		String value = interpreter.stack.getValue();
		if (value.startsWith("\""))
		{
			value = interpreter.removeQuotes(value);	// The reason that I don't do this above is that removeQuotes doesn't check for quotes.
			execute(value, interpreter);
		}
		else
			interpreter.error(new IllegalArgumentException("Not a string; May be caused by end of stack."));
	}
	
	/**
	 * Takes 2 strings and makes a word out of them. The first string is the name of the word and the second is the content of it.
	 */
	@SuppressWarnings("unused")
	private void mkWord(Interpreter interpreter)
	{
		
	}
}
