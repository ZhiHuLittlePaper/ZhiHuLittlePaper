package io.github.zhihulittlepaper.model;

import io.github.zhihulittlepaper.PaperApplication;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.model.IModel.OnDataLoadedListener;
import io.github.zhihulittlepaper.util.Consts;
import io.github.zhihulittlepaper.util.JSONUtils;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView.ScaleType;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NewsListModel implements INewsListModel {

	private RequestQueue queue;

	public NewsListModel() {
		queue = Volley.newRequestQueue(PaperApplication.getApp());
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
	public void loadNewsImage(News news, final OnImageLoadedListener listener) {
		String imageUrl = news.getImage();
		int maxWidth = 60;
		int maxHeight = 60;
		ScaleType scaleType = ScaleType.FIT_XY;
		Config decodeConfig = Config.RGB_565;
		
		Listener<Bitmap> bitmaplistener = new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				listener.onImageLoaded(response);
			}
		};
		
		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onImageLoadFailed(error.toString());
			}
		};
		ImageRequest imageRequest = new ImageRequest(imageUrl, bitmaplistener,
				maxWidth, maxHeight, scaleType, decodeConfig, errorListener);
		queue.add(imageRequest);
	}

}
