package io.github.zhihulittlepaper.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

public interface IStartView extends IView {
	void showImage(Bitmap bm);
	void startNewListActivity(Class<? extends Activity> activity);
	ImageView getImageView();
	
}
