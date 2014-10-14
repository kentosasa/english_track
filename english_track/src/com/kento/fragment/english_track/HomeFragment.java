package com.kento.fragment.english_track;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LineGraph.OnPointClickedListener;
import com.echo.holographlibrary.LinePoint;
import com.example.english_track.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kento.adapter.english_track.CustomPagerAdapter;
import com.kento.db.english_track.Score;

public class HomeFragment extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";

	ArrayList<Score>scores = new ArrayList<Score>();
	TextView maxText;
	int max = 0;

	public static HomeFragment newInstance(int sectionNumber) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public HomeFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		
		maxText = (TextView)rootView.findViewById(R.id.maxText);
		ViewPager pager = (ViewPager)rootView.findViewById(R.id.pager);
		pager.setAdapter(new CustomPagerAdapter(getChildFragmentManager());
		
		SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
		Gson gson = new Gson();
		scores = gson.fromJson(preferences.getString("score",""), new TypeToken<List<Score>>(){}.getType());
		if (scores != null) {
			for (int i = 0; i < scores.size(); i++) {
				Score score = scores.get(i);
				if (max < score.getScore()) max = score.getScore();
			}

			maxText.setText("今までの最高得点　" + max + "点\n今までの受験回数　" + scores.size() + "回");

		}


		Button test_btn = (Button)rootView.findViewById(R.id.test_btn);
		test_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), TestActivity.class);
				startActivity(intent);

			}
		});

		return rootView;
	}
}
