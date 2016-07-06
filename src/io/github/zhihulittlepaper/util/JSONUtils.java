package io.github.zhihulittlepaper.util;

import io.github.zhihulittlepaper.entity.News;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONUtils {

	public static List<News> parseNewsList(String response) {
		List<News> newsList = new ArrayList<News>();
		try {
			JSONObject obj = new JSONObject(response);
			String date = parseDate(obj.getString("date"));
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

	private static String parseDate(String date) {
		StringBuilder sb = new StringBuilder();
		if(date != null && date.length() == 8) {
			sb.append(date.substring(0, 4));
			sb.append("Äê");
			sb.append(date.substring(4, 6));
			sb.append("ÔÂ");
			sb.append(date.substring(6, 8));
			sb.append("ÈÕ");
		}
		return sb.toString();
	}

}
