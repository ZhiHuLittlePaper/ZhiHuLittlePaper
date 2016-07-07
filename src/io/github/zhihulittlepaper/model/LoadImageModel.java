package io.github.zhihulittlepaper.model;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import io.github.zhihulittlepaper.PaperApplication;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class LoadImageModel implements ILoadImageModel {

	private static LoadImageModel model;
	private RequestQueue queue;
	private Map<ImageView, ImageRequest> requestMap;
	private Map<String, SoftReference<Bitmap>> imageMap;

	public static LoadImageModel getModel() {
		if(model == null) {
			model = new LoadImageModel();
		}
		return model;
	}

	private LoadImageModel() {
		queue = Volley.newRequestQueue(PaperApplication.getApp());
		requestMap = new HashMap<ImageView, ImageRequest>();
		imageMap = new HashMap<String, SoftReference<Bitmap>>();
	}

	@Override
	public void loadImage(final ImageView imageView,final String url, final OnImageLoadedListener listener) {
		ImageRequest imageRequest = requestMap.get(imageView);
		if(imageRequest != null) {
			imageRequest.cancel();
			imageRequest = null;
		}

		SoftReference<Bitmap> ref = imageMap.get(url);
		if(ref != null) {
			Bitmap bm = ref.get();
			if(bm != null) {
				listener.onImageLoaded(bm);
				return;
			}
		}

		int maxWidth = 0;
		int maxHeight = 0;
		ScaleType scaleType = ScaleType.FIT_XY;
		Config decodeConfig = Config.RGB_565;

		Listener<Bitmap> bitmaplistener = new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				listener.onImageLoaded(response);
				imageMap.put(url, new SoftReference<Bitmap>(response));
			}
		};
		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onImageLoadFailed(error.toString());
			}
		};

		imageRequest = new ImageRequest(url, bitmaplistener,
				maxWidth, maxHeight, scaleType, decodeConfig, errorListener);
		queue.add(imageRequest);
		requestMap.put(imageView, imageRequest);
	}

}
