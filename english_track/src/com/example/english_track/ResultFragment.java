package com.example.english_track;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.english_track.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kento.db.english_track.Problem;
import com.kento.db.english_track.Score;


public class ResultFragment extends Fragment{
	ArrayList<Score> scores = new ArrayList<Score>();
	
	TextView scoreCalText;
	TextView scoreText;
	TextView jpEnCrtText;
	TextView jpEnCntText;
	TextView enJaCrtText;
	TextView enJaCntText;
	TextView crtCntText;
	TextView probabilityText;
	Button testBtn;
	Button homeBtn;
	ProgressBar progressBar1;
	ProgressBar progressBar2;
	public ResultFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_result, container,
				false);
		scoreCalText = (TextView)rootView.findViewById(R.id.score_cal);
		scoreText = (TextView)rootView.findViewById(R.id.score);
		jpEnCrtText = (TextView)rootView.findViewById(R.id.jp_en_crt);
		jpEnCntText = (TextView)rootView.findViewById(R.id.jp_en_cnt);
		enJaCntText = (TextView)rootView.findViewById(R.id.en_ja_cnt);
		enJaCrtText = (TextView)rootView.findViewById(R.id.en_ja_crt);
		crtCntText = (TextView)rootView.findViewById(R.id.crt_cnt);
		probabilityText = (TextView)rootView.findViewById(R.id.probability);
		homeBtn = (Button)rootView.findViewById(R.id.home_btn);
		testBtn = (Button)rootView.findViewById(R.id.test_btn);
		progressBar1 = (ProgressBar)rootView.findViewById(R.id.progressBar1);
		progressBar2 = (ProgressBar)rootView.findViewById(R.id.progressBar2);
		
		SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
		Gson gson = new Gson();
		scores = gson.fromJson(preferences.getString("score",""), new TypeToken<List<Score>>(){}.getType());
		
		Score score = scores.get(scores.size()-1);
		scoreCalText.setText(score.getCtr_cnt()*5 + "点 - " + score.getMis_cnt()*4 + "点");
		scoreText.setText(score.getCtr_cnt()*5-score.getMis_cnt()*4 + "点");
		progressBar1.setMax(score.getJp_en_cnt());
		progressBar1.setProgress(score.getJp_en_crt_cnt());
		jpEnCrtText.setText(score.getJp_en_crt_cnt()+"");
		jpEnCntText.setText("問正解/" + score.getJp_en_cnt() + "問中");
		enJaCrtText.setText(score.getEn_ja_crt_cnt()+"");
		enJaCntText.setText("問正解/" + score.getEn_ja_cnt() + "問中");
		progressBar2.setMax(score.getEn_ja_cnt());
		progressBar2.setProgress(score.getEn_ja_crt_cnt());
		crtCntText.setText("正答率" + score.getCtr_cnt() + "/" + (score.getCtr_cnt() + score.getMis_cnt()) + "=");
		probabilityText.setText((score.getCtr_cnt()*100/(score.getCtr_cnt()+score.getMis_cnt())) +"");
		homeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
		testBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), TestActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
		
		return rootView;
	}
}
