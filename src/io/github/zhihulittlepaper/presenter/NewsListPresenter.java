package io.github.zhihulittlepaper.presenter;

import java.util.List;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.model.IModel.OnDataLoadedListener;
import io.github.zhihulittlepaper.model.IModel;
import io.github.zhihulittlepaper.model.INewsListModel;
import io.github.zhihulittlepaper.model.NewsListModel;
import io.github.zhihulittlepaper.util.JSONUtils;
import io.github.zhihulittlepaper.view.INewsListView;

public class NewsListPresenter implements INewsListPresenter {
	
	private INewsListView view;
	private INewsListModel model;

	public NewsListPresenter(INewsListView view) {
		this.view = view;
		this.model = new NewsListModel();
	}
	
	@Override
	public void loadNewsList() {
		OnDataLoadedListener listener = new OnDataLoadedListener() {
			@Override
			public void onDataLoaded(String response) {
				List<News> newsList = JSONUtils.parseNewsList(response);
				view.setData(newsList);
			}
			
			@Override
			public void onDataLoadFailed(String error) {
			}
		};
		
		model.loadNewsList(listener);
	}

	@Override
	public void loadNewsImage(final ImageView imageView, News news) {
		model.loadNewsImage(imageView, news, new IModel.OnImageLoadedListener() {
			@Override
			public void onImageLoaded(Bitmap response) {
				imageView.setImageBitmap(response);
			}
			@Override
			public void onImageLoadFailed(String error) {
			}
		});
	}

	@Override
	public void loadMoreNewsList(String date) {
		OnDataLoadedListener listener = new OnDataLoadedListener() {
			@Override
			public void onDataLoaded(String response) {
				List<News> newsList = JSONUtils.parseNewsList(response);
				view.setData(newsList);
			}
			
			@Override
			public void onDataLoadFailed(String error) {
			}
		};
		
		model.loadMoreNewsList(date, listener);
	}
	
	
}
