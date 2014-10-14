package com.kento.fragment.english_track;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.echo.holographlibrary.LineGraph.OnPointClickedListener;
import com.example.english_track.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kento.db.english_track.Score;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LineGraphFragment extends Fragment {
	ArrayList<Score>scores = new ArrayList<Score>();
	int max = 0;
	
//	private static final String ARG_SECTION_NUMBER = "section_number";	
//	public static HomeFragment newInstance(int sectionNumber) {
//		HomeFragment fragment = new HomeFragment();
//		Bundle args = new Bundle();
//		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//		fragment.setArguments(args);
//		return fragment;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_line_graph, container,
				false);
    	Log.e("(,, ﾟДﾟ)", "LineGraphよばれたで");
		SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
		Gson gson = new Gson();
		scores = gson.fromJson(preferences.getString("score",""), new TypeToken<List<Score>>(){}.getType());
		if (scores != null) {
			Line l = new Line();
			for (int i = 0; i < scores.size(); i++) {
				Score score = scores.get(i);
				LinePoint p = new LinePoint();
				p.setX(i);
				p.setY(score.getScore());
				l.addPoint(p);
				if (max < score.getScore()) max = score.getScore();
			}

			l.setColor(Color.parseColor("#FFBB33"));
			LineGraph li = (LineGraph)rootView.findViewById(R.id.graph);
			li.addLine(l);
			li.setRangeY(0, max);
			li.setOnPointClickedListener(new OnPointClickedListener() {

				@Override
				public void onClick(int lineIndex, int pointIndex, float x, float y) {
					// TODO Auto-generated method stub
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
					Toast toast = Toast.makeText(getActivity(), pointIndex+" "+sdf.format(scores.get(pointIndex).getCreated_at()) + " " + scores.get(pointIndex).getScore() + "点", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP|Gravity.LEFT, (int)x, (int)y+380);
					toast.show();

				}
			});

		}
		return rootView;
	}

}
