package com.abner.playground.framework.mybatis;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
	@Select("SELECT * FROM blog WHERE id=#{id}")
	Blog selectBlog(int id);
	
}
