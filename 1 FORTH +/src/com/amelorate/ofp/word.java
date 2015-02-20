package com.amelorate.ofp;

public class word 
{
	public String[] content;
	
	/**
	 * The built in method used to execute the word if the word is built in and can not be made using other words.
	 */
	public String nativeExecuteMethod;
	
	public word(String[] content)
	{
		this.content = content;
	}
	
	public word(String[] content, String nativeExecuteMethod)
	{
		this.content = content;
		this.nativeExecuteMethod = nativeExecuteMethod;
	}
	
	/**
	 * Executes the word.
	 * @param interpreter
	 * The interpreter to use to execute the word.
	 */
	public void Execute(Interpreter interpreter)
	{
		if (content[0] == "native")
			interpreter.methodDecoder.execute(nativeExecuteMethod, interpreter);
		else
		{
			for (int i = 0; i < content.length; i++)
			{
				interpreter.executeLine(content[i]);
			}
		}
	}
}
