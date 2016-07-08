package io.github.zhihulittlepaper.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	
	private Context context;
	private List<T> data;
	private LayoutInflater inflater;

	public BaseAdapter(Context context, List<T> data) {
		setContext(context);
		setData(data);
		setInflater();
	}
	
	public final Context getContext() {
		return context;
	}
	public final void setContext(Context context) {
		if(context == null) {
			throw new IllegalAccessError();
		}
		this.context = context;
	}
	public final List<T> getData() {
		return data;
	}
	public final void setData(List<T> data) {
		if(data == null) {
			data = new ArrayList<T>();
		}
		this.data = data;
	}
	public final LayoutInflater getInflater() {
		return inflater;
	}
	private void setInflater() {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public final int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
