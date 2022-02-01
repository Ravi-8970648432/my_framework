package com.vitiger.comcast.genericUtility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;
/**
 * This class contains java specific generic library
 * @author RaviKumara NR
 *
 */

public class JavaUtility {
	/**
	 * this method will generate random number and returns into the caller
	 * @return
	 */

	public int getRandomNumber()
	{
		Random ran=new Random();
		int randnum=ran.nextInt(1000);
		return randnum;
	}
	/**
	 * this method will return current System date
	 * @return
	 */
	public String getCurrentSystemDate()
	{
		Date date=new Date();
		String currentDate=date.toString();
		return currentDate;
	}
	/**
	 * this method will return date in specified format
	 * @return
	 */
	public String getFinalDateFormat()
	{
		Date date=new Date();
		String d = date.toString();
		String[] dte=d.split(" ");
		String YYYY=dte[5];
		String DD=dte[2];
		String MM=dte[1];
		String today=YYYY+"-"+MM+"-"+DD;
		return today;
	}
    /**
     * used to pass Virtual Key to OS
     * @throws Throwable
     */
    public void pressVurtualEnterKey() throws Throwable {
    	
    	Robot rc=new Robot();
    	rc.keyPress(KeyEvent.VK_ENTER);
    	rc.keyRelease(KeyEvent.VK_ENTER);
    }


}
