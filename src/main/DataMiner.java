package main;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataMiner {
	public static void findValueOf(String currency)throws IOException
	{
		File output = new File("Data.txt");
		PrintWriter out = new PrintWriter(output);
		DataMiner.datafinder(currency, out);
		System.out.println(DataMiner.analyze("Data.txt"));
	}
	
	public static void datafinder(String Currency, PrintWriter out) throws IOException
	{
		URL url = new URL("https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=" + Currency);
		URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        Scanner in = new Scanner(is);
        while(in.hasNext())
        {
        	out.println(in.nextLine());
        }
        in.close();
	}
	
	public static double analyze(String fileName) throws IOException
	{
		File input = new File(fileName);
		Scanner in = new Scanner(input);
		String temp;
		double value=0;
		while(in.hasNext())
		{
			temp=in.next();
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
