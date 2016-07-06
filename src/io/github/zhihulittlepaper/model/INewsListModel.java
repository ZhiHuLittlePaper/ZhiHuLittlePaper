package io.github.zhihulittlepaper.model;

import io.github.zhihulittlepaper.entity.News;

public interface INewsListModel extends IModel {
	void loadNewsList(OnDataLoadedListener listener);
	void loadNewsImage(News news, OnImageLoadedListener listener);
}
