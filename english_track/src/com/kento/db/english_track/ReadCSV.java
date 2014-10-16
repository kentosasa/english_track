package com.kento.db.english_track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

public class ReadCSV {

	@SuppressWarnings("null")
	public static void parse(Context context) {
		ArrayList<Problem> problemList = new ArrayList<Problem>();
		AssetManager assetManager = context.getResources().getAssets();
		try {
			InputStream is = assetManager.open("data.csv");
			InputStreamReader inputStreamReader = new InputStreamReader(is, "SJIS");
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			String line = "";
			int n = 0;
			int id = 0;
			while ((line = bufferReader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				if(st.hasMoreTokens()){
					String first = st.nextToken();
					if (st.hasMoreTokens()) {
						String second = st.nextToken();
						problemList.add(new Problem(first, second, id));
						n++;
						id++;
						Log.e("CSV", n + first + ", " + second);
					}
				}
			}
			SharedPreferences preferences = context.getSharedPreferences("key", Activity.MODE_PRIVATE);
			Gson gson = new Gson();
			preferences.edit().putString("problem", gson.toJson(problemList)).commit();
			bufferReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
