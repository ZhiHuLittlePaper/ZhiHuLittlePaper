package io.github.zhihulittlepaper.util;

import io.github.zhihulittlepaper.entity.News;
import io.github.zhihulittlepaper.entity.NewsDetail;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONUtils {

	public static List<News> parseNewsList(String data) {
		List<News> newsList = new ArrayList<News>();
		try {
			JSONObject obj = new JSONObject(data);
			String date = obj.getString("date");
			JSONArray arr = obj.getJSONArray("stories");
			int length = arr.length();
			for(int i = 0; i< length; i++) {
				JSONObject objNews = arr.getJSONObject(i);
				News news = new News();
				news.setId(objNews.getString("id"));
				news.setTitle(objNews.getString("title"));
				news.setImage(fixUrl(objNews.getString("images")));
				news.setDate(date);
				newsList.add(news);
			}
			return newsList;
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String fixUrl(String string) {
		string = string.substring(2, string.length() - 2);
		String sub;
		String url;
		sub = string.substring(0, 5);
		url = sub + "//";
		sub = string.substring(9, 23);
		url = url + sub + "/";
		sub = string.substring(25);
		url = url + sub;
		Log.i("tedu", "url=" + url);
		return url;
	}

	public static NewsDetail parseNewsDetail(String data) {
		NewsDetail detail = new NewsDetail();
		try {
			JSONObject obj = new JSONObject(data);
			detail.setBody(obj.getString("body"));
			detail.setImageSource(obj.getString("image_source"));
			detail.setImage(obj.getString("image"));
			return detail;
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
