package com.huitao.dsq.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String data1="2016-07-18 8:46:32";
         String data2="2016-07-20 8:46:32";
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
         Date dateStart;
		try {
			dateStart = sdf.parse(data1);
			Date dateEnd = sdf.parse(data2);
	         System.out.println("dateStart"+dateStart.getTime());
	         System.out.println("dateEnd"+dateEnd.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*535753499302
		6100d01af11c0424ad8ab044c118ca08bd4a48386355def740700517
		23384603
		b66d924d7c5dd4d1f8615c93a8a5f418*/
         
	}

}
