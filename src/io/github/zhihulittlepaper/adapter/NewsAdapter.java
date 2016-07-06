package io.github.zhihulittlepaper.adapter;

import java.util.List;

import javax.crypto.spec.PSource;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.presenter.INewsListPresenter;

public class NewsAdapter extends BaseAdapter<News> {

	private INewsListPresenter presenter;

	public NewsAdapter(Context context, List<News> news, INewsListPresenter presenter) {
		super(context, news);
		this.presenter = presenter;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		News news = getItem(position);
		ViewHolder holder;
		if(convertView == null) {
			convertView = getInflater().inflate(R.layout.item_news, null);

			holder = new ViewHolder();
			holder.tvDate = (TextView) convertView.findViewById(R.id.tv_story_date);
			holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_story_image);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_story_title);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position == 0 || !news.getDate().equals(getItem(position - 1).getDate())) {
			holder.tvDate.setText(news.getDate());
			holder.tvDate.setVisibility(View.VISIBLE);
		}
		else {
			holder.tvDate.setVisibility(View.GONE);
		}
		
		holder.tvTitle.setText(news.getTitle());
		holder.ivImage.setImageBitmap(null);
		presenter.loadNewsImage(holder.ivImage, news);

		return convertView;
	}

	class ViewHolder {
		TextView tvDate;
		ImageView ivImage;
		TextView tvTitle;
	}


}
