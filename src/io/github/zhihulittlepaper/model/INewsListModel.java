package io.github.zhihulittlepaper.model;

import android.widget.ImageView;
import io.github.zhihulittlepaper.entity.News;

public interface INewsListModel extends IModel {
	void loadNewsList(OnDataLoadedListener listener);
	void loadNewsImage(ImageView imageView, News news, OnImageLoadedListener listener);
	void loadMoreNewsList(String date, OnDataLoadedListener listener);
}
