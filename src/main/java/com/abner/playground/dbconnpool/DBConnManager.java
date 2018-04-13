package com.abner.playground.dbconnpool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

public final class DBConnManager {
	private static DBConnManager instance;
	private static DruidDataSource druidDataSource;
	
	private DBConnManager() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/com/abner/playground/dbconnpool/conn.properties");
		prop.load(fis);
		initDruidDataSource(prop);
	}

	private void initDruidDataSource(Properties prop) {
		druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(prop.getProperty("driver"));
		druidDataSource.setUrl( prop.getProperty("url"));
		druidDataSource.setUsername(prop.getProperty("username"));
		druidDataSource.setPassword(prop.getProperty("password"));
		druidDataSource.setMaxActive(Integer.parseInt(prop.getProperty("maxActive")));
	}
	
	public synchronized static final DBConnManager getInstance() throws IOException{
		if(instance == null){
			instance = new DBConnManager();
		}
		return instance;
	}
	
	public synchronized final Connection getConnection() throws SQLException{
		Connection conn = null;
		conn = druidDataSource.getConnection();
		return conn;
	}
}
