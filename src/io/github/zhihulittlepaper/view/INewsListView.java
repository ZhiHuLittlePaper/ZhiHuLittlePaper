package io.github.zhihulittlepaper.view;

import java.util.List;

import io.github.zhihulittlepaper.entity.News;

public interface INewsListView extends IView {
	void setData(List<News> news);
}
