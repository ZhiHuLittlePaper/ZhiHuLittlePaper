package io.github.zhihulittlepaper.activity;

import java.io.Serializable;

import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.R.layout;
import io.github.zhihulittlepaper.R.menu;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.presenter.INewsDetailPresenter;
import io.github.zhihulittlepaper.presenter.NewsDetailPresenter;
import io.github.zhihulittlepaper.util.Consts;
import io.github.zhihulittlepaper.view.INewDetailView;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.webkit.WebView;

public class NewsDetailActivity extends Activity implements INewDetailView {

	private WebView wvNewsDetail;
	private INewsDetailPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		
		wvNewsDetail = (WebView) findViewById(R.id.wv_news_detail);
		News news = (News) getIntent().getSerializableExtra("selectedNews");
		setData(news);
		
//		presenter = new NewsDetailPresenter(this);
//		presenter.loadNewsDetail(news);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_detail, menu);
		return true;
	}

	@Override
	public void setData(News news) {
		wvNewsDetail.loadUrl("http://daily.zhihu.com/story/8531854");
	}

}
