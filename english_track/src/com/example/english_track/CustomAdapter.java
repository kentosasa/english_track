package com.example.english_track;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.kento.db.english_track.Problem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
	  
    private LayoutInflater inflater;
    private ArrayList<Problem> list;
    private Context context;
    
    // コンストラクタ
    public CustomAdapter(Context context, ArrayList<Problem> miss) {
    	inflater = LayoutInflater.from(context);
		list = miss;
		this.context = context;
		if (list == null) list = new ArrayList<Problem>(); 
    }
 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
        View view = convertView;
        
        // Viewを再利用している場合は新たにViewを作らない
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
        }
 
        // 特定の行のデータを取得
        TextView en_text = (TextView)view.findViewById(R.id.en_text);
        TextView ja_text = (TextView)view.findViewById(R.id.ja_text);
        Button delete = (Button)view.findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				list.remove(position);
				SharedPreferences preferences = context.getSharedPreferences("key", Activity.MODE_PRIVATE);
				Gson gson = new Gson();
				preferences.edit().putString("miss", gson.toJson(list)).commit();
				notifyDataSetChanged();
			}
		});
        en_text.setText(list.get(position).getEn());
        ja_text.setText(list.get(position).getJp());
        
        return view;
	}
}