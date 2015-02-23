package com.amelorate.ofp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell 
{
	private static Interpreter interpreter = new Interpreter();
	private static BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	private static String input;
	
	public static void main(String[] args) {
		try
		{
			interpreter.executeLine("\"helloWorld\" exmethod");
		}
		catch (Exception e)
		{
			System.out.println("Interprter crashed with: " + e.toString());
			System.out.println("Reseting interpreter...");
			interpreter = new Interpreter();
		}
		
		System.out.print("1 FORTH + Shell \n>");
		while (true)
		{
			try 
			{
				input = bufferRead.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			if (input == "exit")
				break;
			else
			{
				try
				{
					interpreter.executeLine(input);
				}
				catch (Exception e)
				{
					System.out.println("Interprter crashed with: " + e.toString());
					System.out.println("Reseting interpreter...");
					interpreter = new Interpreter();
				}
			}
			
			System.out.print(">");
		}
	}
}
