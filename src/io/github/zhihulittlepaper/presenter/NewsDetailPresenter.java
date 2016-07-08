package io.github.zhihulittlepaper.presenter;

import android.graphics.Bitmap;
import android.widget.ImageView;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.model.IModel.OnDataLoadedListener;
import io.github.zhihulittlepaper.model.IModel.OnImageLoadedListener;
import io.github.zhihulittlepaper.model.INewsDetailModel;
import io.github.zhihulittlepaper.model.NewsDetailModel;
import io.github.zhihulittlepaper.util.JSONUtils;
import io.github.zhihulittlepaper.view.INewDetailView;

public class NewsDetailPresenter implements INewsDetailPresenter {

	private INewDetailView view;
	private INewsDetailModel model;

	public NewsDetailPresenter(INewDetailView view) {
		this.view = view;
		this.model = new NewsDetailModel();
	}

	@Override
	public void loadNewsDetail(final News news) {
		OnDataLoadedListener listener = new OnDataLoadedListener() {
			@Override
			public void onDataLoaded(String response) {
				news.setDetail(JSONUtils.parseNewsDetail(response));
				view.setData(news);
			}
			
			@Override
			public void onDataLoadFailed(String error) {
			}
		};
		model.loadNewsDetail(news, listener);
	}

	@Override
	public void loadDetailImage(final ImageView imageView, News news) {
		OnImageLoadedListener listener = new OnImageLoadedListener() {
			@Override
			public void onImageLoaded(Bitmap response) {
				imageView.setImageBitmap(response);
			}
			@Override
			public void onImageLoadFailed(String error) {
			}
		};
		
		model.loadDetailImage(imageView, news, listener);
	}

}
