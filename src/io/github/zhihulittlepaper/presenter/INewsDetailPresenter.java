package io.github.zhihulittlepaper.presenter;

import android.widget.ImageView;
import io.github.zhihulittlepaper.entity.News;

public interface INewsDetailPresenter extends IPresenter {

	void loadNewsDetail(News news);

	void loadDetailImage(ImageView imageView, News news);

}
