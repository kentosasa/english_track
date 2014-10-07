package com.example.english_track;


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new IntroFragment()).commit();
		}
		
		CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				View rootView = findViewById(android.R.id.content);
				TextView intro_text = (TextView)rootView.findViewById(R.id.intro_text);
				intro_text.setText((int)millisUntilFinished/1000+"");
			}
			
			@Override
			public void onFinish() {
				android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.container, new TestFragment()).commit();				
			}
		};
		countDownTimer.start();
	}
}
