package com.amelorate.ofp;

public class word 
{
	public String[] content;
	
	/**
	 * The built in method used to execute the word if the word is built in and can not be made using other words.
	 */
	public String nativeExecuteMethod;
	MethodDecoder methodDecoder = new MethodDecoder();
	
	public word(String[] content)
	{
		this.content = content;
	}
	
	public word(String[] content, String nativeExecuteMethod)
	{
		this.content = content;
		this.nativeExecuteMethod = nativeExecuteMethod;
	}
	
	public void Execute(Interpreter interpreter)
	{
		if (content[0] != "native")
			for (int i = 0; i < content.length; i++)
			{
				interpreter.executeLine(content[i]);
			}
		else
		{
			methodDecoder.execute(nativeExecuteMethod, interpreter);
		}
	}
}
