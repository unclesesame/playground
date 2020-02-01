package com.abner.playground.java8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8TestClient {
	
	private void init(){
		
	}
	
	
	private Set<String> getDistictTags (List<Article> articles){
		return articles.stream().flatMap(article -> article.getTags().stream()).collect(Collectors.toSet());
	}
	
	
	public static void main(String[] args) {
		
		
		int r = (int) (Math.random() * 1);
		System.out.println(r);
	}
}
