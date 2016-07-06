package io.github.zhihulittlepaper.model;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.zhihulittlepaper.PaperApplication;
import io.github.zhihulittlepaper.activity.NewsListActivity;
import io.github.zhihulittlepaper.model.IModel.OnDataLoadedListener;
import io.github.zhihulittlepaper.util.Consts;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView.ScaleType;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoadImageModel implements ILoadImageModel {

	private RequestQueue queue;

	public LoadImageModel() {
		queue = Volley.newRequestQueue(PaperApplication.getApp());
	}
	
	@Override
	public void loadStartImage(final OnImageLoadedListener listener) {
		
		String url = Consts.URL_START_IMAGE;
		final ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onImageLoadFailed(error.toString());
			}
		};
		Listener<String> stringListener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					JSONObject obj = new JSONObject(response);
					String imageUrl = obj.getString("img");
					loadImage(imageUrl, listener, errorListener);
				} 
				catch (JSONException e) {
					e.printStackTrace();
					listener.onImageLoadFailed(null);
				}
			}
		};
		
		StringRequest stringRequest = new StringRequest(url, stringListener, errorListener);
		
		queue.add(stringRequest);
		
	}
	
	private void loadImage(String imageUrl, final OnImageLoadedListener listener, final ErrorListener errorListener) {
		int maxWidth = 480;
		int maxHeight = 800;
		ScaleType scaleType = ScaleType.FIT_XY;
		Config decodeConfig = Config.RGB_565;
		
		Listener<Bitmap> bitmaplistener = new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				listener.onImageLoaded(response);
			}
		};
		ImageRequest imageRequest = new ImageRequest(imageUrl, bitmaplistener,
				maxWidth, maxHeight, scaleType, decodeConfig, errorListener);
		queue.add(imageRequest);
	}

}
