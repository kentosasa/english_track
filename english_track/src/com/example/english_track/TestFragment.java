package com.example.english_track;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kento.english_track.R;

import android.R.style;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TestFragment extends Fragment {
	ProgressBar progressBar;
	TextView timeText;
	TextView problemText;
	Button[] choices = new Button[4];
	ArrayList<Problem> problemList;
	ArrayList<Problem> miss;
	int correctNum = 0;
	Random rnd = new Random();
	ImageView maru;
	ImageView batu;
	boolean sleepFrag = false;
	boolean jp_to_en = true;
	int count = 0;
	int score = 0;
	int crt_cnt = 0;
	int mis_cnt = 0;
	int jp_en_cnt = 0;
	int jp_en_crt_cnt = 0;
	int en_ja_cnt = 0;
	int en_ja_crt_cnt = 0;
	SleepNextProblem sleepNextProblem;

	public TestFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_test, container,
				false);
		SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
		Gson gson = new Gson();
		problemList = gson.fromJson(preferences.getString("problem",""), new TypeToken<List<Problem>>(){}.getType());
		miss = gson.fromJson(preferences.getString("miss",""), new TypeToken<List<Problem>>(){}.getType());
		if (miss == null) miss = new ArrayList<Problem>(); 
		maru = (ImageView)rootView.findViewById(R.id.maru);
		batu = (ImageView)rootView.findViewById(R.id.batu);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
		progressBar.setMax(60);
		timeText = (TextView)rootView.findViewById(R.id.time_text);
		problemText = (TextView)rootView.findViewById(R.id.problem_text);
		choices[0] = (Button)rootView.findViewById(R.id.ans_btn1);
		choices[1] = (Button)rootView.findViewById(R.id.ans_btn2);
		choices[2] = (Button)rootView.findViewById(R.id.ans_btn3);
		choices[3] = (Button)rootView.findViewById(R.id.ans_btn4);

		for (int i = 0; i < choices.length; i++) {
			choices[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!sleepFrag) {
						Log.e("hoge", "hoge");
						switch (v.getId()) {
						case R.id.ans_btn1:
						case R.id.ans_btn2:
						case R.id.ans_btn3:
						case R.id.ans_btn4:
							choices[correctNum].setBackgroundColor(getResources().getColor(R.color.button));
							choices[correctNum].setTextColor(getResources().getColor(R.color.White));
							if (jp_to_en) {
								jp_en_cnt++;
								if (((Button)v).getText().toString().contains(problemList.get(correctNum).getEn())){
									Log.e("正解", "正解");
									crt_cnt++;
									jp_en_crt_cnt++;
									maru.setVisibility(View.VISIBLE);
								}else{
									mis_cnt++;
									Log.e("不正解", "不正解");
									miss.add(0, problemList.get(correctNum));
									batu.setVisibility(View.VISIBLE);
								}															
							}else{
								en_ja_cnt++;
								if (((Button)v).getText().toString().contains(problemList.get(correctNum).getJp())){
									Log.e("正解", "正解");
									en_ja_crt_cnt++;
									crt_cnt++;
									maru.setVisibility(View.VISIBLE);
								}else{
									mis_cnt++;
									Log.e("不正解", "不正解");
									miss.add(0, problemList.get(correctNum));
									batu.setVisibility(View.VISIBLE);
								}								
							}
							break;
						default:
							break;
						}
						sleepNextProblem = new SleepNextProblem(1500, 500);
						sleepNextProblem.start();
					}
				}
			});
		}

		CountDownTimer countDownTimer = new MyCountDownTimer(60000, 1000);
		countDownTimer.start();
		NextProblem();
		return rootView;
	}

	public void NextProblem() {
		maru.setVisibility(View.GONE);
		batu.setVisibility(View.GONE);
		Collections.shuffle(problemList);
		count++;
		if (count%4 == 0) {
			if (jp_to_en) {
				jp_to_en = false;
			}else{
				jp_to_en = true;
			}
		}
		correctNum = rnd.nextInt(4);
		if (jp_to_en) {
			problemText.setText(problemList.get(correctNum).getJp());
			for (int i = 0; i < choices.length; i++) {
				choices[i].setText(problemList.get(i).getEn());
			}
		}else{
			problemText.setText(problemList.get(correctNum).getEn());
			for (int i = 0; i < choices.length; i++) {
				choices[i].setText(problemList.get(i).getJp());
			}
		}
	}

	public class SleepNextProblem extends CountDownTimer {

		public SleepNextProblem(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			sleepFrag = true;
		}

		@Override
		public void onFinish() {
			NextProblem();
			sleepFrag = false;
			for (int i = 0; i < choices.length; i++) {
				choices[i].setBackgroundColor(getResources().getColor(R.color.White));
				choices[i].setTextColor(getResources().getColor(R.color.Black));
			}
		}
		@Override
		public void onTick(long millisUntilFinished) {
		}
	}
	public class MyCountDownTimer extends CountDownTimer{
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			progressBar.setProgress((int) (millisUntilFinished/1000));
			timeText.setText("残り時間 " + millisUntilFinished/1000 + "秒");
		}

		@Override
		public void onFinish() {
			SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
			Gson gson = new Gson();
			ArrayList<Score> scores = gson.fromJson(preferences.getString("score",""), new TypeToken<List<Score>>(){}.getType());
			score = crt_cnt * 10 - mis_cnt * 4;
			if (sleepNextProblem != null) sleepNextProblem.cancel();
	        if (scores == null) {
	        	scores = new ArrayList<Score>();
			}
			scores.add(new Score(score, crt_cnt, mis_cnt, jp_en_cnt, jp_en_crt_cnt, en_ja_cnt, en_ja_crt_cnt));
			preferences.edit().putString("score", gson.toJson(scores)).commit();
			preferences.edit().putString("miss", gson.toJson(miss)).commit();

			android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.container, new ResultFragment()).commit();		
		}
	}
}
