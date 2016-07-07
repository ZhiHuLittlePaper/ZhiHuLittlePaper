package io.github.zhihulittlepaper.model;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.widget.ImageView;
import io.github.zhihulittlepaper.PaperApplication;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.util.Consts;

public class NewsDetailModel implements INewsDetailModel {
	
	private RequestQueue queue;
	private ILoadImageModel model;

	public NewsDetailModel() {
		queue = Volley.newRequestQueue(PaperApplication.getApp());
		model = LoadImageModel.getModel();
	}

	@Override
	public void loadNewsDetail(News news, final OnDataLoadedListener listener) {
		String url = Consts.URL_BASE_NEWS + news.getId();
		Listener<String> successListener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				listener.onDataLoaded(response);
			}
		};
		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onDataLoadFailed(error.toString());
			}
		};
		StringRequest request = new StringRequest(url, successListener, errorListener);
		queue.add(request);
	}

	@Override
	public void loadDetailImage(ImageView imageView, News news, final OnImageLoadedListener listener) {
		String url = news.getDetail().getImage();
		model.loadImage(imageView, url, listener);
	}

}
