package com.amelorate.ofp;

public class Shell 
{
	private static Interpreter interpreter = new Interpreter();
	
	public static void main(String[] args) {
		interpreter.executeLine("\"helloWorld\" exmethod");
	}
}
