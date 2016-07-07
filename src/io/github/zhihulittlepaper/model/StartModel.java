package io.github.zhihulittlepaper.model;


import org.json.JSONException;
import org.json.JSONObject;

import io.github.zhihulittlepaper.PaperApplication;
import io.github.zhihulittlepaper.util.Consts;

import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class StartModel implements IStartModel {

	private ILoadImageModel model;
	private RequestQueue queue;

	public StartModel() {
		queue = Volley.newRequestQueue(PaperApplication.getApp());
		model = LoadImageModel.getModel();
	}

	@Override
	public void loadStartImageUrl(final OnDataLoadedListener listener) {

		String url = Consts.URL_START_IMAGE;
		final ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onDataLoaded(error.toString());
			}
		};
		Listener<String> stringListener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					String imageUrl = obj.getString("img");
					listener.onDataLoaded(imageUrl);
				} 
				catch (JSONException e) {
					e.printStackTrace();
					listener.onDataLoadFailed(null);
				}
			}
		};

		StringRequest stringRequest = new StringRequest(url, stringListener, errorListener);

		queue.add(stringRequest);

	}

	@Override
	public void loadImage(final ImageView imageView,final String url, final OnImageLoadedListener listener) {
		model.loadImage(imageView, url, listener);
	}

}
