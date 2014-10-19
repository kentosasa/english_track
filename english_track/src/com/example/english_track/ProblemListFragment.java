package com.example.english_track;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kento.english_track.R;

public class ProblemListFragment extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	ArrayList<Score>scores = new ArrayList<Score>();
	ArrayList<Problem> miss = new ArrayList<Problem>(); 
	TextView maxText;
	public static ProblemListFragment newInstance(int sectionNumber) {
		ProblemListFragment fragment = new ProblemListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ProblemListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_problem_list, container, false);
				
		SharedPreferences preferences = getActivity().getSharedPreferences("key", Activity.MODE_PRIVATE);
		Gson gson = new Gson();
		scores = gson.fromJson(preferences.getString("score",""), new TypeToken<List<Score>>(){}.getType());

		miss = gson.fromJson(preferences.getString("miss",""), new TypeToken<List<Problem>>(){}.getType());
		// ListViewのインスタンスを取得
        ListView list = (ListView)rootView.findViewById(R.id.listView);
        CustomAdapter mAdapter = new CustomAdapter(getActivity(), miss);

        // リストにAdapterをセット
        list.setAdapter(mAdapter);
         
        // リストアイテムの間の区切り線を非表示にする
		return rootView;
	}
}
