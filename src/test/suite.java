/**
 * 
 */
package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * @author xantris
 *
 */
public class suite {
	
	static String driver64;
	static String driver32;
	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	private void loadparam()
	{
		String filename = "webdriver.properties";
		InputStream webdrin = null;
		Properties webdr = new Properties();
		
		try {
			webdrin = getClass().getClassLoader().getResourceAsStream(filename);
			if (webdrin == null) 
			{
				System.out.println("Sorry, unable to find " + filename);
				return;
			}
			webdr.load(webdrin);
			suite.driver32=webdr.getProperty("driver32");
			suite.driver64=webdr.getProperty("driver64");
		 }
		catch (IOException ex) 
		{
				ex.printStackTrace();
		}
		finally
		{
			try
			{
			webdrin.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		suite suite = new suite();
		suite.loadparam();
		System.setProperty("allowNaticeXPath", "false");
		System.setProperty("webdriver.ie.driver", driver32);
		suite.executeThemAll();
	}	
	
	private void executeThemAll() 
	{
		double []result= new double[5];
		perf_test Tests = new perf_test();
		Properties prop = new Properties();
		InputStream input = null;
		try 
		{
			String filename = "config2.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) 
			{
				System.out.println("Sorry, unable to find " + filename);
				return;
			}
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			int size=prop.size();
			int stato=0;
			int skip=0;
			while (e.hasMoreElements()) 
			{
				stato++;
				String key = (String) e.nextElement();
				System.out.println("Zona: " + key);
				if (skip <=0)
				{
					(new Thread(new DialogWindow())).start();
					String value = prop.getProperty(key);
					Tests.link=value;
					result[0] = Tests.open_map();
					System.out.println("Open map: " + result[0]);
					result[1] = Tests.zoom();
					System.out.println("Tooltip : "+ result[1]);

					result[2] = Tests.tooltip();
					System.out.println("Zoom: " + result[2]);

					result[3] = Tests.map_pan();
					System.out.println("Map pan: " + result[3]);

					result[4] = Tests.print();		  
					System.out.println("Print: " + result[4]);
					new report_excell().writeXLSXFile(key,result);
					if (Tests.sfondo()!=0){
						System.out.println("Test  Mappe di sfondo KO");
						
					}else{
						System.out.println("Test  Mappe di sfondo OK");

					}
					if (Tests.area()){
						System.out.println("Test Misura Area OK");
					}else{
						System.out.println("Test Misura Area KO");
					}
					if (Tests.lineare()){
						System.out.println("Test Misura Lineare OK");
					}else{
						System.out.println("Test Misura Lineare KO");
					}	
					try 
					{
						TimeUnit.SECONDS.sleep(1);
					} 
					catch (InterruptedException ex) 
					{
						System.out.println(key + " completata.");
					}
					System.out.println(key + " completata.");
				}
				skip--;
				System.out.println("Completati: " + stato+"/"+size);
			}
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			if (input != null) 
			{
				try 
				{
					input.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}