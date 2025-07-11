package com.abner.playground.javacore.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotationDemo {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		
		Map<Integer, String> map =  new HashMap<>();
		Class klass = Teacher.class;
		Field[] fields = klass.getDeclaredFields();
		System.out.println(klass.getField("sex"));
		for(Field field : fields){
			Column column = field.getAnnotation(Column.class);
			if(column == null)
				continue;
			Integer index = column.index();
			String fieldName = field.getName();
			String header = column.header().isEmpty() ? fieldName : column.header();
			map.put(index, header);
			System.out.println("index : " + index + " header : " + header);
		}
	}
	
}
