package io.github.zhihulittlepaper.presenter;

import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.model.IModel.OnDataLoadedListener;
import io.github.zhihulittlepaper.model.INewsDetailModel;
import io.github.zhihulittlepaper.model.NewsDetailModel;
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
//		OnDataLoadedListener listener = new OnDataLoadedListener() {
//			@Override
//			public void onDataLoaded(String response) {
//				view.setData(response);
//				news.setBody(response);
//			}
//			
//			@Override
//			public void onDataLoadFailed(String error) {
//				view.setData("404 not fund");
//			}
//		};
//		model.loadNewsDetail(news, listener);
	}

}
