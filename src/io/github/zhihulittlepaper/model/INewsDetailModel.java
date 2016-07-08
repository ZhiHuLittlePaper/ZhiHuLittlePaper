package io.github.zhihulittlepaper.model;

import android.widget.ImageView;
import io.github.zhihulittlepaper.entity.News;

public interface INewsDetailModel extends IModel {
	void loadNewsDetail(News news, OnDataLoadedListener listener);

	void loadDetailImage(ImageView imageView, News news, OnImageLoadedListener listener);
}
