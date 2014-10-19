package com.example.english_track;


import android.app.Activity;
import android.os.Bundle;
import com.kento.english_track.R;

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
