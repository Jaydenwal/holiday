package com.wal.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class FileProperties {
	
	public static String getAntvPath()
	{
		Properties p = new Properties();
		
		try
		{
			p.load(new FileInputStream("../application.properties"));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p.getProperty("url");
	}

}
