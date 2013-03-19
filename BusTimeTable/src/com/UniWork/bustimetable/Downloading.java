package com.UniWork.bustimetable;

import systemManagment.XmlReader;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Downloading extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downloading);
		XmlReader getter = new XmlReader();
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_downloading, menu);
		return true;
	}

}
