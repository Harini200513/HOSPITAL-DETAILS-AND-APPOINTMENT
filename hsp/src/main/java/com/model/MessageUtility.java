package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MessageUtility {

	public static void sendPassword(String mobilenumber,String message)
	{
		String url = "http://www.smsgatewaycenter.com/library/send_sms_2.php?UserName=ayushren&Password=anudeep&Type=Individual&To="+mobilenumber+"&Mask=IDEA20&Message=yourpasswordis::::"+message;
		
		try {
			URL url2 = new URL(url);
			try {
				URLConnection connection = url2.openConnection();
			InputStream inputStream = 	connection.getInputStream();
			int i;
			while ((i=inputStream.read()) != -1) {
				System.out.print((char)i);
				
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
