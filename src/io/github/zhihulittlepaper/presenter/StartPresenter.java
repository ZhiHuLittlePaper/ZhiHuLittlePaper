package io.github.zhihulittlepaper.presenter;

import android.graphics.Bitmap;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import io.github.zhihulittlepaper.activity.NewsListActivity;
import io.github.zhihulittlepaper.model.ILoadImageModel;
import io.github.zhihulittlepaper.model.IModel.OnDataLoadedListener;
import io.github.zhihulittlepaper.model.IModel.OnImageLoadedListener;
import io.github.zhihulittlepaper.model.IStartModel;
import io.github.zhihulittlepaper.model.LoadImageModel;
import io.github.zhihulittlepaper.model.StartModel;
import io.github.zhihulittlepaper.view.IStartView;

public class StartPresenter implements IStartPresenter {

	private IStartView view;
	private IStartModel model;

	public StartPresenter(IStartView view) {
		this.view = view;
		model = new StartModel();
	}
	
	@Override
	public void loadImage() {
		
		final OnImageLoadedListener imagelistener = new OnImageLoadedListener() {
			@Override
			public void onImageLoaded(Bitmap response) {
				view.showImage(response);
				new Thread() {
					@Override
					public void run() {
						try {
							sleep(1500);
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
		
		OnDataLoadedListener listener = new OnDataLoadedListener() {
			@Override
			public void onDataLoaded(String response) {
				model.loadImage(view.getImageView(), response, imagelistener);
			}
			
			@Override
			public void onDataLoadFailed(String error) {
			}
		};
		model.loadStartImageUrl(listener);
		
		
		
	}

}
