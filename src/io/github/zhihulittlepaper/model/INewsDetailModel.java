package io.github.zhihulittlepaper.model;

import io.github.zhihulittlepaper.entity.News;

public interface INewsDetailModel extends IModel {
	void loadNewsDetail(News news, OnDataLoadedListener listener);
}
