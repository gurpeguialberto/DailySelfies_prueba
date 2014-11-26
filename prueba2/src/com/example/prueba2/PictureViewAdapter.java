package com.example.prueba2;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
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
	
	/*   constructor    */
	public PictureViewAdapter(Context context){
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}
	
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
		TextView picture_day;
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
			holder.picture_day = (TextView) newView.findViewById(R.id.picture_day);
			holder.picture_time = (TextView) newView.findViewById(R.id.picture_time);
			newView.setTag(holder);
			
		} else {
			holder = (ViewHolder) newView.getTag();
		}
		holder.picture.setImageBitmap(curr.getmPicture());
		holder.picture_name.setText("Uri: " + curr.getmPicUri());
		holder.picture_time.setText("Date: " + curr.getmTimeStamp());
		holder.picture_day.setText("Time: " + curr.getmDayStamp());
		return newView;
	}
	public void add(PictureRecord listItem){
		list.add(listItem);
		notifyDataSetChanged();
	}
	public ArrayList<PictureRecord> getList(){
		return list;
	}
	public void removeAllViews(){
		list.clear();
		this.notifyDataSetChanged();
	}

}
