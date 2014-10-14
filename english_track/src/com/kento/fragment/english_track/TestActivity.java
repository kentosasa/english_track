package com.kento.fragment.english_track;


import com.example.english_track.R;

import android.app.Activity;
import android.os.Bundle;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new IntroFragment()).commit();
		}
	}
}
