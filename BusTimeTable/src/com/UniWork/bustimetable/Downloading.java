package com.UniWork.bustimetable;

import java.io.FileNotFoundException;

import systemManagment.XmlReader;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

public class Downloading extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downloading);
		XmlReader getter = new XmlReader();
		try {
			
			String[] settings =getter.getInfo(this.openFileInput("settings.dat"));
			if (settings[1].equalsIgnoreCase("t")){// has data
				next();
			}
			
			if (settings[0].equalsIgnoreCase("t")){ // auto update
				///check version of xml
			}
			
		} catch (FileNotFoundException e) {
			try {
				getter.createSettings(this.openFileOutput("settings.dat", Context.MODE_PRIVATE));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		
	}

	private void next() {
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_downloading, menu);
		return true;
	}

}
