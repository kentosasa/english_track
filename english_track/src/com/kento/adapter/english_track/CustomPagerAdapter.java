package com.kento.adapter.english_track;

import java.util.ArrayList;
import java.util.Locale;

import com.echo.holographlibrary.LineGraph;
import com.example.english_track.R;
import com.kento.fragment.english_track.HomeFragment;
import com.kento.fragment.english_track.LineGraphFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CustomPagerAdapter extends FragmentPagerAdapter {


	public CustomPagerAdapter(FragmentManager childFragmentManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	public void destroyItem(ViewGroup container, int position, Object object){
		
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return new LineGraphFragment();
	}

}
