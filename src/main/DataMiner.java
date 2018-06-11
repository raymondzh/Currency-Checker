package main;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.Spring;

public class DataMiner {
	public static void valueTimer()throws IOException
	{
		Scanner in = new Scanner(System.in);
		Timer timer = new Timer();
		System.out.println("How many currencies do you want to track");
		int numCurrencies = Utility.errorCheck(1, in); //Error trap for number of currencies tracked
		double[][] values = new double[numCurrencies][2]; // array for values of curriencies for comparisons
		String[] names = new String[numCurrencies]; // array for names of currencies to check
		char repeat = 'y'; // Used to see if the check should repeat
		System.out.println("Please enter the currency codes you would like to track one by one");
		
		//Gets the currency codes that the user wants to track, based off of number of currencies
		for(int i = 0; i < numCurrencies; i++)
		{
			names[i] = in.next(); 
		}
				
		
		// searches and displays the value of currencies
		while(repeat  == 'Y' || repeat == 'y')
		{
			System.out.println("Loading...");
			for(int i = 0; i < numCurrencies; i++)
			{
				if(DataMiner.datafinder(names[i])!=1)
				{
					values[i][0] = values[i][1];
					values[i][1] = datafinder(names[i]);
					
				}
				System.out.print(names[i] + ": " + values[i][1] + "    ");
				System.out.printf("%.9f",values[i][1]-values[i][0]);
				System.out.println();
			}
			System.out.println("Do you want to check again? (Y/N)");
			repeat = in.next().charAt(0);
			
			
		}
		
	}
	
	public static double datafinder(String Currency) throws IOException
	{
		URL url = new URL("https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=" + Currency);
		String temp;
	    double value= 0;
	  
		try
		{
			URLConnection con = url.openConnection();
	        InputStream is = con.getInputStream();
	        Scanner in = new Scanner(is);
	       
	        while(in.hasNext())
	        {
	        	temp = in.next();
	        	if(compareWords(temp, "USD"))
				{
					temp = in.next();
					if(compareWords(temp, "="))
					{
						try
						{
							value = in.nextDouble();
						}
						catch(InputMismatchException e)
						{
							in.next();
						}
					}
				}
	        }
	        in.close();
		}
		catch(IOException e)
		{}
		return value;
        
	}
	
	
	
	public static boolean compareWords(String word, String reference)
	{
		if(word.length() != reference.length())
		{
			return false;
		}
		for(int i = 0; i < word.length()-1; i++)
		{
			if(word.charAt(i) != reference.charAt(i))
			{
				return false;
			}
		}
		return true;
	}
	
	

}
