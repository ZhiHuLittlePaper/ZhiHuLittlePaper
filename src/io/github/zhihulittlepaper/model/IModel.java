package io.github.zhihulittlepaper.model;

import android.graphics.Bitmap;


public interface IModel {
	public interface OnDataLoadedListener {
		void onDataLoaded(String response);
		void onDataLoadFailed(String error);
	}
	public interface OnImageLoadedListener {
		void onImageLoaded(Bitmap response);
		void onImageLoadFailed(String error);
	}
}
