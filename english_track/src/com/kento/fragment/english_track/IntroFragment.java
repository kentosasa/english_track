package com.kento.fragment.english_track;

import com.example.english_track.R;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IntroFragment extends Fragment{

	public IntroFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_intro, container,
				false);
		CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
			@Override
			public void onTick(long millisUntilFinished) {
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
		return rootView;
	}
}
