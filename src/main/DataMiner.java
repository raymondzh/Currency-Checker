package main;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

public class DataMiner {
	public static void valueTimer()throws IOException
	{
		Scanner in = new Scanner(System.in);
		Timer timer = new Timer();
		System.out.println("How many currencies do you want to track");
		int numCurrencies = Utility.errorCheck(1, in);
		double[][] values = new double[numCurrencies][2];
		String[] names = new String[numCurrencies];
		double temp;
		System.out.println("Please enter the currency codes you would like to track one by one");
		for(int i = 0; i < numCurrencies; i++)
		{
			names[i] = in.next();
		}
		for(int i = 0; i < numCurrencies; i++)
		{
			if(DataMiner.findValueOf(names[i])!=1)
			{
				values[i][1] = findValueOf(names[i]);
				values[i][0] = findValueOf(names[i]);
			}
			System.out.println(names[i] + ": " + values[i][1]);
		}
		while(true)
		{
			for(int i  = 0; i < numCurrencies; i++)
			{
				temp=findValueOf(names[i]);
				if(temp!=1)
				{
					values[i][0]=values[i][1];
				}
				System.out.println(names[i] + ": " + values[i][1]);
			}
			
		}
	}
	
	public static double findValueOf(String currency)throws IOException
	{
		File output = new File("Data.txt");
		PrintWriter out = new PrintWriter(output);
		return(DataMiner.datafinder(currency, out));
	}
	
	public static double datafinder(String Currency, PrintWriter out) throws IOException
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
