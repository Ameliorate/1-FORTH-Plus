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
		interpreter.executeLine("\"helloWorld\" exmethod");
		
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
				interpreter.executeLine(input);
			
			System.out.print(">");
		}
	}
}
