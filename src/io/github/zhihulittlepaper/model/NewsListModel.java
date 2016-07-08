package io.github.zhihulittlepaper.model;

import io.github.zhihulittlepaper.PaperApplication;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.util.Consts;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NewsListModel implements INewsListModel {

	private RequestQueue queue;
	private ILoadImageModel model;

	public NewsListModel() {
		queue = Volley.newRequestQueue(PaperApplication.getApp());
		model = LoadImageModel.getModel();
	}
	
	@Override
	public void loadNewsList(final OnDataLoadedListener loadListener) {
		String url = Consts.URL_NEWS_LIST_LATEST;
		Listener<String> listener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				loadListener.onDataLoaded(response);
			}
		};
		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				loadListener.onDataLoadFailed(error.toString());
			}
		};
		StringRequest request = new StringRequest(url, listener, errorListener);
		queue.add(request);
	}

	@Override
	public void loadNewsImage(ImageView imageView, News news, final OnImageLoadedListener listener) {
		String url = news.getImage();
		model.loadImage(imageView, url, listener);
	}

	@Override
	public void loadMoreNewsList(String date, final OnDataLoadedListener loadListener) {
		String url = Consts.URL_BASE_MORE_NEWS_LIST + date;
		Listener<String> listener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				loadListener.onDataLoaded(response);
			}
		};
		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				loadListener.onDataLoadFailed(error.toString());
			}
		};
		StringRequest request = new StringRequest(url, listener, errorListener);
		queue.add(request);
	}

}
