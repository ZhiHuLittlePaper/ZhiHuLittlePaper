package io.github.zhihulittlepaper.view;

import android.app.Activity;
import android.graphics.Bitmap;

public interface IStartView extends IView {
	void showImage(Bitmap bm);
	void startNewListActivity(Class<? extends Activity> activity);
	
}
