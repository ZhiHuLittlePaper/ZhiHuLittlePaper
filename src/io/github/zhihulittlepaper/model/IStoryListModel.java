package io.github.zhihulittlepaper.model;

import java.util.List;

import io.github.zhihulittlepaper.entity.Story;

public interface IStoryListModel extends IModel {
	List<Story> loadStoryList();
}
