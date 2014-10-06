package com.example.english_track;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LineGraph.OnPointClickedListener;
import com.echo.holographlibrary.LinePoint;

public class HomeFragment extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";

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
		Line l = new Line();
		LinePoint p = new LinePoint();
		p.setX(0);
		p.setY(0);
		l.addPoint(p);
		p = new LinePoint();
		p.setX(8);
		p.setY(8);
		l.addPoint(p);
		p = new LinePoint();
		p.setX(10);
		p.setY(4);
		l.addPoint(p);
		l.setColor(Color.parseColor("#FFBB33"));
		LineGraph li = (LineGraph)rootView.findViewById(R.id.graph);
		li.addLine(l);
		li.setRangeY(0, 10);
		li.setOnPointClickedListener(new OnPointClickedListener() {
			
			@Override
			public void onClick(int lineIndex, int pointIndex, float x, float y) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getActivity(), "2014/10/6 190“_", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP|Gravity.LEFT, (int)x, (int)y+380);
				Log.e("X, Y", "X:" + x + " Y:" + y);
				Log.e("lineIndex, pointIndex",lineIndex +", "+ pointIndex);
				toast.show();
				
			}
		});
		
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
