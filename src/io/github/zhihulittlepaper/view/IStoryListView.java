package io.github.zhihulittlepaper.view;

import java.util.List;

import io.github.zhihulittlepaper.entity.Story;

public interface IStoryListView extends IView {
	void setData(List<Story> storys);
	
	void addData(List<Story> storys);
}
