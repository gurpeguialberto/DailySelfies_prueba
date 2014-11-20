package com.example.prueba2;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureViewAdapter extends BaseAdapter{

	private ArrayList<PictureRecord> list = new ArrayList<PictureRecord>();
	private static LayoutInflater inflater = null;
	private Context mContext;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	static class ViewHolder {
		
		ImageView picture;
		TextView picture_name;
		TextView picture_time;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View newView = convertView;
		ViewHolder holder;

		PictureRecord curr = list.get(position);
		if (null == convertView) {
			holder = new ViewHolder();
			newView = inflater.inflate(R.layout.picture_item_view, null);
			holder.picture = (ImageView) newView.findViewById(R.id.picture);
			holder.picture_name = (TextView) newView.findViewById(R.id.picture_name);
			holder.picture_time = (TextView) newView.findViewById(R.id.picture_time);
			newView.setTag(holder);
			
		} else {
			holder = (ViewHolder) newView.getTag();
		}
		holder.picture.setImageBitmap(curr.getmPicture());
		holder.picture_name.setText("Picture name: " + curr.getmPicName());
		holder.picture_time.setText("Picture time: " + curr.getmDate());

		return newView;
	}

}
