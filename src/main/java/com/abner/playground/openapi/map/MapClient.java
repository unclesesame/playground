package com.abner.playground.openapi.map;

import java.net.URL;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class MapClient {
	
	public static final String ENCODING = "UTF-8";
	public static final String KEY="bc8c34a58fbe7f6e38b20d6975c030cf";
	public static final String GEO_BASE_URL = "http://restapi.amap.com/v3/geocode/geo?address=#{}&output=JSON&key=" + KEY;
	
	public static void main(String[] args) throws Exception {
		
		MapClient map = new MapClient();
		map.getCoordinateByAddress("上海市浦东新区顺河路126弄");
		
	}
	
	private Coordinate getCoordinateByAddress(String address) throws IOException{
		String formatedAddress = URLEncoder.encode(address,ENCODING);
		String wsUrl = GEO_BASE_URL.replace("#{}", formatedAddress);
		URL url = new URL(wsUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		InputStreamReader out = new InputStreamReader(urlConnection.getInputStream());
		BufferedReader in = new BufferedReader(out);
		String result = in.readLine(); 
		System.out.println(result);
		JSONObject jsonObject = JSONObject.parseObject(result);
		
		JSONArray jsonArray = jsonObject.getJSONArray("geocodes"); 
		JSONObject object = (JSONObject) jsonArray.get(0);
		System.out.println(object.getString("location"));
		
		return null;
	}
	
	
}
