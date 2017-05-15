package test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class perf_test {
	

	String link ="";
	WebDriver a = new InternetExplorerDriver();
	//WebDriver a = new  PhantomJSDriver(suite2.caps);
public double open_map(){

	long start = System.currentTimeMillis();
	a.get(link);
	a.manage().window().maximize();
	a.switchTo().frame("GeoSearchPane_IFRAME");
	WebDriverWait wait = new WebDriverWait(a, 120);		
	@SuppressWarnings("unused")
	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ui-accordion-header-icon ui-icon apri']")));
	a.switchTo().defaultContent();
	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
	new WebDriverWait(a, 120).until((ExpectedCondition<Boolean>) wd ->((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	long finish = System.currentTimeMillis();
	double totalTime = (finish - start)/1000.0; 
	return totalTime;
	
}
public double tooltip()
{
	WebElement element=a.findElement(By.xpath("//*[contains(@id ,'OpenLayers.Layer.Vector_')]"));
	long start = System.currentTimeMillis();
	element.click();
	WebDriverWait wait = new WebDriverWait(a, 120);
	element =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='maptipContainer']")));		
	a.switchTo().frame("ifrmMapTooltip");
	new WebDriverWait(a, 30).until((ExpectedCondition<Boolean>) wd ->((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*contains[@id,'textTooltip']")));
	long finish = System.currentTimeMillis();
	double totalTime = (finish - start)/1000.0; 
	a.switchTo().defaultContent();
	return totalTime;
}
public double zoom ()
{
	WebElement element_input = a.findElement(By.xpath("//*[@class='inputEditableScale']"));
	element_input.clear();
	element_input.sendKeys("144448");
	element_input.sendKeys(Keys.RETURN);
	WebDriverWait wait = new WebDriverWait(a, 120);
	@SuppressWarnings("unused")
	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
	element_input.clear();
	element_input.sendKeys("2257");
	element_input.sendKeys(Keys.RETURN);
	long start = System.currentTimeMillis();
	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
	long finish = System.currentTimeMillis();
	double totalTime = (finish - start)/1000.0; 
	return totalTime;
}
public double map_pan()
	{
	WebElement element_pan = a.findElement(By.xpath("//img[@class='jxButtonIcon pan']"));
	element_pan.click();
	Actions act = new Actions(a);
	WebElement element = a.findElement(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]"));
	act.moveToElement(element,200,50).click().keyDown(Keys.SHIFT).clickAndHold().moveToElement(element, 600, 350).click().release().keyUp(Keys.SHIFT).build().perform();
	long start = System.currentTimeMillis();
	WebDriverWait wait = new WebDriverWait(a, 120);
	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
	long finish = System.currentTimeMillis();
	double totalTime = (finish - start)/1000.0; 
	return totalTime;
	}
public double print()
{
	WebElement element = a.findElement(By.xpath("//*[@title='Crea la tua stampa personalizzata']"));
	element.click();
	a.switchTo().frame("TaskPane_IFRAME");
	WebDriverWait wait = new WebDriverWait(a, 120);		
	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Stampa']")));
	new Select(a.findElement(By.id("PaperList"))).selectByVisibleText("A3 (297x420 mm )");
	Actions builder = new Actions( a );
	Action  action  = builder.click(a.findElement(By.id("chkLegend"))).build();
	action.perform();
	action = builder.click(a.findElement(By.id("chkArrow"))).build();
	action.perform();
	action = builder.click(a.findElement(By.id("chkCartiglio"))).build();
	action.perform();
	String parentHandle = a.getWindowHandle();
	long start = System.currentTimeMillis();
	element.click();
	try {
		  TimeUnit.SECONDS.sleep(5);
	  } catch (InterruptedException ex) {
		  System.out.println("");
	  }
	Set<String> s=a.getWindowHandles();
	Iterator<String> ite=s.iterator();

	while(ite.hasNext())
	{
	    String popupHandle=ite.next().toString();
	    if(!popupHandle.contains(parentHandle))
	    {
	    	a.switchTo().window(popupHandle);
	        a.close();
	    	a.switchTo().window(parentHandle);
	    }
	}
	long finish = System.currentTimeMillis();
	double totalTime = (finish - start)/1000.0; 
	//a.close();
	a.switchTo().frame("TaskPane_IFRAME");
	element = a.findElement(By.xpath("//*[@title='Annulla stampa']"));
	element.click();
	return totalTime;
		}
public double sfondo(){
	WebDriverWait wait = new WebDriverWait(a, 120);		
	WebElement element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	//element=a.findElement(By.id("//[@class=jxMenuContainer jxHasChrome]")); 
	element = a.findElement(By.xpath("//*[contains(text(), 'Google Maps Satellite')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	element = a.findElement(By.xpath("//*[contains(text(), 'Google Maps Hybrid')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	element = a.findElement(By.xpath("//*[contains(text(), 'Bing Maps Street')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	element = a.findElement(By.xpath("//*[contains(text(), 'Bing Maps Satellite')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	element = a.findElement(By.xpath("//*[contains(text(), 'Bing Maps Hybrid')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	element = a.findElement(By.xpath("//*[contains(text(), 'Open Street Map Street')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	element = a.findElement(By.xpath("//*[@title='Cambia la mappa di sfondo']"));
	element.click();
	element = a.findElement(By.xpath("//*[contains(text(), 'NONE')]"));
	element.click();
	try{
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]")));
		}catch (TimeoutException exception) {
	        return -1;
	    }
	return 0;
}
public boolean area(){
	WebElement element = a.findElement(By.xpath("//*[@title='Misura areale']"));
	element.click();
	Actions act = new Actions(a);
	element = a.findElement(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]"));
	act.moveToElement(element,400,150).click().click().moveByOffset(200, 100).click().click().moveByOffset(-100, 200).release().doubleClick().build().perform();
	//element = a.findElement(By.xpath("*[contains(@class ,'divMeasureMarker')");
	if(a.findElements(By.xpath("//*[contains(@class ,'divMeasureMarker')]")).size() > 0 ){
		return true;
	}else{
		return false;
	}
	
}
public boolean lineare(){
	WebElement element = a.findElement(By.xpath("//*[@title='Misura lineare']"));
	element.click();
	Actions act = new Actions(a);
	element = a.findElement(By.xpath("//*[contains(@src ,'mapagent/mapagent.fcgi') and contains(@class, 'olTileImage') and contains(@id, 'OpenLayersDiv')]"));
	act.moveToElement(element,400,150).click().click().moveByOffset(200, 100).click().click().moveByOffset(-100, 200).release().doubleClick().build().perform();
	//element = a.findElement(By.xpath("*[contains(@class ,'divMeasureMarker')");
	if(a.findElements(By.xpath("//*[contains(@class ,'divMeasureMarker')]")).size() > 0 ){
		return true;
	}else{
		return false;
	}
	
}
}