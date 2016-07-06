package io.github.zhihulittlepaper.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.entity.Story;

public class StoryAdapter extends BaseAdapter<Story> {

	public StoryAdapter(Context context, List<Story> storys) {
		super(context, storys);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Story story = getItem(position);
		ViewHolder holder;
		if(convertView == null) {
			convertView = getInflater().inflate(R.layout.item_story, null);
			
			holder = new ViewHolder();
			holder.tvDate = (TextView) convertView.findViewById(R.id.tv_story_date);
			holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_story_image);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_story_title);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// TODO
		
		return convertView;
	}
	
	class ViewHolder {
		TextView tvDate;
		ImageView ivImage;
		TextView tvTitle;
	}

}
