package io.github.zhihulittlepaper.model;

import android.widget.ImageView;

public interface ILoadImageModel extends IModel {
	void loadImage(ImageView imageView, String url, OnImageLoadedListener listener);
}
