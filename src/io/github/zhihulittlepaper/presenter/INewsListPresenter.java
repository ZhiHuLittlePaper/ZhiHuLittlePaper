package io.github.zhihulittlepaper.presenter;

import io.github.zhihulittlepaper.entity.News;
import android.widget.ImageView;

public interface INewsListPresenter extends IPresenter {
	void loadNewsList();
	void loadNewsImage(ImageView imageView, News news);
	void loadMoreNewsList(String date);
}
