package io.github.zhihulittlepaper.activity;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.R.layout;
import io.github.zhihulittlepaper.R.menu;
import io.github.zhihulittlepaper.adapter.NewsAdapter;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.presenter.INewsListPresenter;
import io.github.zhihulittlepaper.presenter.NewsListPresenter;
import io.github.zhihulittlepaper.view.INewsListView;

public class NewsListActivity extends Activity implements INewsListView  {

	private ListView lvNews;
	private NewsAdapter adapter;
	private List<News> newsList;
	private INewsListPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_list);
		
		lvNews = (ListView)findViewById(R.id.lv_news);
		presenter = new NewsListPresenter(this);
		presenter.loadNewsList();
		setListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_list, menu);
		return true;
	}

	@Override
	public void setData(List<News> news) {
		if(this.newsList == null) {
			this.newsList = news;
			adapter = new NewsAdapter(this, news, presenter);
			lvNews.setAdapter(adapter);
		}
		else {
			this.newsList.clear();
			this.newsList.addAll(news);
			if(adapter == null) {
				adapter = new NewsAdapter(this, news, presenter);
				lvNews.setAdapter(adapter);
			}
			else {
				adapter.notifyDataSetChanged();
			}
		}
	}
	
	private void setListeners() {
		lvNews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				News selectedNews = adapter.getItem(position);
				startNewsDetailActivity(selectedNews);
			}
		});
	}

	private void startNewsDetailActivity(News selectedNews) {
		Intent intent = new Intent(this, NewsDetailActivity.class);
		intent.putExtra("selectedNews", selectedNews);
		startActivity(intent);
	}

}