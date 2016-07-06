package io.github.zhihulittlepaper.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import io.github.zhihulittlepaper.R;
import io.github.zhihulittlepaper.R.layout;
import io.github.zhihulittlepaper.R.menu;

public class StoryListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.story_list, menu);
		return true;
	}

}
