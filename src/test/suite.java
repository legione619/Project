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
	static String driver64="C:\\Users\\xantris\\Desktop\\selenium\\IEDriverServer_x64_3.0.0\\IEDriverServer.exe";
	static String driver32="C:\\Users\\xantris\\Desktop\\selenium\\IEDriverServer_Win32_3.0.0\\IEDriverServer.exe";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("allowNaticeXPath", "false");
		System.setProperty("webdriver.ie.driver", driver32);
		suite suite = new suite();
		suite.executeThemAll();
	}		
		  private void executeThemAll() {
			  double []result= new double[5];
			  perf_test Tests = new perf_test();
			  Properties prop = new Properties();
			  InputStream input = null;
			  try {
				  String filename = "config2.properties";
				  input = getClass().getClassLoader().getResourceAsStream(filename);
				  if (input == null) {
					  System.out.println("Sorry, unable to find " + filename);
					  return;
					}
				  prop.load(input);
				  Enumeration<?> e = prop.propertyNames();
				  int size=prop.size();
				  int stato=0;
				  int skip=0;
				  while (e.hasMoreElements()) {
					  stato++;
					  String key = (String) e.nextElement();
					  System.out.println("Zona: " + key);
					  if (skip <=0){
						  (new Thread(new DialogWindow())).start();
						  String value = prop.getProperty(key);
						  Tests.link=value;
						  result[0] = Tests.open_map();
						  result[0] = Tests.entra_esci();
						  result[1] = Tests.zoom();
						  result[2] = Tests.tooltip();
						  result[3] = Tests.map_pan();
						  result[4] = Tests.print();		  
						  System.out.println("Open map: " + result[0]);
						  System.out.println("Tooltip : "+ result[1]);
						  System.out.println("Zoom: " + result[2]);
						  System.out.println("Map pan: " + result[3]);
						  System.out.println("Print: " + result[4]);
						  new report_excell().writeXLSXFile(key,result);
						  try {
							  TimeUnit.SECONDS.sleep(1);
						  } catch (InterruptedException ex) {
							  System.out.println(key + " completata.");
					  }
					  System.out.println(key + " completata.");
					  }
					  skip--;
					  System.out.println("Completati: " + stato+"/"+size);
				  }
			  } catch (IOException ex) {
				  ex.printStackTrace();
			  } finally {
				  if (input != null) {
					  try {
						  input.close();
					  } catch (IOException e) {
						  e.printStackTrace();
					  }
				  }
			  }
		  }
	}