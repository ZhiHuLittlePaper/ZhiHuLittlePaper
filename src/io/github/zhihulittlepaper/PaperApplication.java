package io.github.zhihulittlepaper;

import android.app.Application;

public class PaperApplication extends Application {
	
	
	private static PaperApplication app;
	
	@Override
	public void onCreate() {
		app = this;
	}

	public static PaperApplication getApp() {
		return app;
	}
	
}
