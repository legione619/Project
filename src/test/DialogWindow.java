package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.lang.InterruptedException;

public class DialogWindow implements Runnable {
	public void run() {
	    try {
	        entercredentials();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}	
	public void entercredentials() throws InterruptedException {
	    Thread.sleep(3000);
	    try {
	        //enterText("RISORSE\apri253");
	        //enterSpecialChar(KeyEvent.VK_TAB);
	        enterText("WqJeDl$7");
//	        enterText("graz1a_0317");

	        //enterSpecialChar(KeyEvent.VK_ENTER);

	    } catch (AWTException e) {

	    }
	}
	private void enterText(String text) throws AWTException, InterruptedException {
	    Robot robot = new Robot();
	    StringSelection pwd = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        //press enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

	}
	
}
