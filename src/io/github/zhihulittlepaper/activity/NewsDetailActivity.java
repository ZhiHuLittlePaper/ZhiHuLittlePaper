package io.github.zhihulittlepaper.activity;


import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.presenter.INewsDetailPresenter;
import io.github.zhihulittlepaper.presenter.NewsDetailPresenter;
import io.github.zhihulittlepaper.util.Consts;
import io.github.zhihulittlepaper.view.INewDetailView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class NewsDetailActivity extends Activity implements INewDetailView {

	private WebView wvNewsDetail;
	private ScrollView svNewsDetail;
	private ImageView ivDetailImage;
	private TextView tvDetailContent;
	private INewsDetailPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);

		setViews();

		News news = (News) getIntent().getSerializableExtra("selectedNews");


		presenter = new NewsDetailPresenter(this);
		presenter.loadNewsDetail(news);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_detail, menu);
		return true;
	}

	@Override
	public void setData(News news) {
		//		showInScrollView(news);

		showInWebView(news);
	}

	private void setViews () {
		wvNewsDetail = (WebView) findViewById(R.id.wv_news_detail);
		WebSettings settings = wvNewsDetail.getSettings();
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		settings.setJavaScriptEnabled(true);
		wvNewsDetail.setVerticalScrollBarEnabled(false); 
		wvNewsDetail.setHorizontalScrollBarEnabled(false); 


		svNewsDetail = (ScrollView) findViewById(R.id.sv_news_detail);
		ivDetailImage = (ImageView) findViewById(R.id.iv_news_detail_image);
		tvDetailContent = (TextView) findViewById(R.id.tv_news_detail_content);
		
		wvNewsDetail.setVisibility(View.GONE);
		svNewsDetail.setVisibility(View.GONE);
	}

	private void showInWebView(News news) {
		wvNewsDetail.setVisibility(View.VISIBLE);
		svNewsDetail.setVisibility(View.GONE);

		String headerImage; 
		if (news.getDetail().getImage() == null || news.getDetail().getImage() == "") { 
			headerImage = "'" + R.drawable.ic_launcher + "'"; 

		}
		else { 
			headerImage = news.getDetail().getImage(); 
		} 
		StringBuilder sb = new StringBuilder(); 
		sb.append("<div class=\"img-wrap\">").append("<h1 class=\"headline-title\">") 
		.append(news.getTitle()).append("</h1>").append("<span class=\"img-source\">") 
		.append(news.getDetail().getImageSource()).append("</span>") 
		.append("<img src=\"").append(headerImage).append("\" alt=\"\">") 
		.append("<div class=\"img-mask\"></div>"); 
		String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>" 
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>" 
				+ news.getDetail().getBody().replace("<div class=\"img-place-holder\">", sb.toString()); 
		wvNewsDetail.loadDataWithBaseURL("", mNewsContent, "text/html", "UTF-8", null); 

		//		wvNewsDetail.loadUrl(Consts.URL_BASE_NEWS_WEBVIEW + news.getId());
	}

	private void showInScrollView(News news) {
		wvNewsDetail.setVisibility(View.GONE);
		svNewsDetail.setVisibility(View.VISIBLE);

		new AsyncTask<String, Void, Spanned> () {
			@Override
			protected Spanned doInBackground(String... params) {
				Spanned text = Html.fromHtml(params[0]);
				return text;
			}
			@Override
			protected void onPostExecute(Spanned result) {
				tvDetailContent.setText(result);
			}
		}.execute(news.getDetail().getBody());

		presenter.loadDetailImage(ivDetailImage, news);
	}
}
