package io.github.zhihulittlepaper.model;

import android.widget.ImageView;

public interface IStartModel extends IModel {
	void loadStartImageUrl(OnDataLoadedListener listener);
	void loadImage(ImageView imageView, String url, OnImageLoadedListener listener);
}
