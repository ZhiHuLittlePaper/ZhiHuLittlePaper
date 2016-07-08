package io.github.zhihulittlepaper.view;



import io.github.zhihulittlepaper.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PullListView extends ListView implements AbsListView.OnScrollListener {

	private final static int STATE_NO_DONE = 0;
	private final static int STATE_DONE = 1;
	private final static int STATE_PULL = 2;
	private final static int STATE_RELEASE = 3;
	private final static int STATE_REFRESHING = 4;
	private int currentRefreshState;
	private boolean isRefreshable;
	private boolean isLoadable;
	private int downY;

	private View vHeader;
	private TextView tvRefreshState;
	private int headerHeight;
	private DataRefresher dataRefresher;
	private MoreDataLoader moreDataLoader;
	private View vFooter;
	private TextView tvLoadState;
	private int footerHeight;

	public PullListView(Context context) {
		this(context, null);
	}
	public PullListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public PullListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnScrollListener(this);
		init(context);
	}


	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		vHeader = inflater.inflate(R.layout.header_pull_list_view, null);
		tvRefreshState = (TextView) vHeader.findViewById(R.id.tv_refresh_state);
		tvRefreshState.setText("");

		addHeaderView(vHeader);
		vHeader.measure(0, 0);
		headerHeight = vHeader.getMeasuredHeight();
		vHeader.setPadding(0, -headerHeight, 0, 0);
		isRefreshable = true;
		currentRefreshState = STATE_DONE;

		vFooter = View.inflate(context, R.layout.footer_pull_list_view, null);
		tvLoadState = (TextView) vFooter.findViewById(R.id.tv_load_state);

		footerHeight = vFooter.getMeasuredHeight();
		addFooterView(vFooter);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(isRefreshable) {
				downY = (int) ev.getY();
				currentRefreshState = STATE_PULL;
				tvRefreshState.setText("下拉刷新");
			}
			if(isLoadable) {
				downY = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(currentRefreshState == STATE_PULL || currentRefreshState == STATE_RELEASE) {
				int moveY = (int) ev.getY();
				vHeader.setPadding(0, moveY - downY - headerHeight, 0, 0);
				if(moveY - downY >= headerHeight) {
					currentRefreshState = STATE_RELEASE;
					tvRefreshState.setText("松开刷新");
				}
				else {
					currentRefreshState = STATE_PULL;
					tvRefreshState.setText("下拉刷新");
				}
				
			}
			if(isLoadable) {
				int moveY = (int) ev.getY();
				if(downY - moveY > 60) {
					moreDataLoader.loadMore();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.i("tedu", "ACTION_UP");
			Log.i("tedu", currentRefreshState + "," + isRefreshable);
			if(currentRefreshState == STATE_RELEASE) {
				tvRefreshState.setText("正在刷新");
				currentRefreshState = STATE_REFRESHING;

				if(dataRefresher != null) {
					dataRefresher.refresh();
				}
				else {
					vHeader.setPadding(0, -headerHeight, 0, 0);
					currentRefreshState = STATE_DONE;
				}
			}
			else if(currentRefreshState == STATE_PULL){
				currentRefreshState = STATE_DONE;
				vHeader.setPadding(0, -headerHeight, 0, 0);
			}
			break;
		}

		return super.onTouchEvent(ev);
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);

		vHeader.setPadding(0, -headerHeight, 0, 0);
		currentRefreshState = STATE_DONE;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if(firstVisibleItem == 0) {
			isRefreshable = true;
			if(currentRefreshState == STATE_NO_DONE) {
				currentRefreshState = STATE_DONE;
			}
		}
		else {
			isRefreshable = false;
			currentRefreshState = STATE_NO_DONE;
		}

		if(firstVisibleItem + visibleItemCount >= totalItemCount) {
			isLoadable = true;
		}
		else {
			isLoadable = false;
		}
	}

	public void setMoreDataLoader(MoreDataLoader loader) {
		this.moreDataLoader = loader;
	}
	public void setDataRefresher(DataRefresher refresher) {
		this.dataRefresher = refresher;
	}

	public interface DataRefresher {
		void refresh();
	}

	public interface MoreDataLoader {
		void loadMore();
	}

}
