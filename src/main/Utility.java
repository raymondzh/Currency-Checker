package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {
	public static int errorCheck(int min, int max, Scanner in){
		int a;
		while(true){ // level error trapping
			try{
				 a = in.nextInt();
			}
			catch(InputMismatchException e){
				in.next();
				a = max+1;; // making it a not valid level
			}
			if(a >= min && a <= max){
				return a;
			}
			else{
				System.out.println("That is not a valid input");
				System.out.println("Please try again");
			}
		}
	}
	
	public static double errorCheck(double min, double max, Scanner in){
		double a;
		while(true){ // level error trapping
			try{
				 a = in.nextInt();
			}
			catch(InputMismatchException e){
				in.next();
				a = max+1;; // making it a not valid level
			}
			if(a >= min && a <= max){
				return a;
			}
			else{
				System.out.println("That is not a valid input");
				System.out.println("Please try again");
			}
		}
	}
	
	public static int random(int min, int max){
		return (int)(Math.random() * (Math.abs(max - min) + 1) + ((max >= min) ? min : max));
	}
	
	public static int digitAt(int number, int n){
		return (number / (int)Math.pow(10,Integer.toString(number).length()-n-1))%10;
	}
	
	public static char getChar(Scanner in){
		return in.next().charAt(0);
		
	}
	public static int add(int a, int b){
		return a+b;
	}
}












