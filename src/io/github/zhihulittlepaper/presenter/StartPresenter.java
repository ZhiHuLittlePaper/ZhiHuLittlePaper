package io.github.zhihulittlepaper.presenter;

import android.graphics.Bitmap;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import io.github.zhihulittlepaper.activity.NewsListActivity;
import io.github.zhihulittlepaper.model.ILoadImageModel;
import io.github.zhihulittlepaper.model.IModel.OnImageLoadedListener;
import io.github.zhihulittlepaper.model.LoadImageModel;
import io.github.zhihulittlepaper.view.IStartView;

public class StartPresenter implements IStartPresenter {

	private IStartView view;
	private ILoadImageModel model;

	public StartPresenter(IStartView view) {
		this.view = view;
		model = new LoadImageModel();
	}
	
	@Override
	public void loadImage() {
		
		OnImageLoadedListener listener = new OnImageLoadedListener() {
			@Override
			public void onImageLoaded(Bitmap response) {
				view.showImage(response);
				new Thread() {
					@Override
					public void run() {
						try {
							sleep(2000);
							view.startNewListActivity(NewsListActivity.class);
						} 
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
			@Override
			public void onImageLoadFailed(String error) {
//				view.startNewListActivity(NewsListActivity.class);
			}
		};
		model.loadStartImage(listener);
	}

}
