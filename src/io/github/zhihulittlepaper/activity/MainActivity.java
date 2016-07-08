package io.github.zhihulittlepaper.activity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.presenter.IStartPresenter;
import io.github.zhihulittlepaper.presenter.StartPresenter;
import io.github.zhihulittlepaper.view.IStartView;

public class MainActivity extends Activity implements IStartView{

	private ImageView ivStart;
	private IStartPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivStart = (ImageView) findViewById(R.id.iv_start);
		presenter = new StartPresenter(this);
		presenter.loadImage();
	}

	@Override
	public void showImage(Bitmap bm) {
		ivStart.setImageBitmap(bm);
	}

	@Override
	public void startNewListActivity(Class<? extends Activity> activity) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
		finish();
	}
	
	@Override
	public  ImageView getImageView() {
		return ivStart;
	}

}
