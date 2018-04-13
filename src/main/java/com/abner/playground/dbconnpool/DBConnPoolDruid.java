package com.abner.playground.dbconnpool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnPoolDruid {
	public static void main(String[] args) throws SQLException, IOException {
		//使用Druid
		for(int i=0; i < 20; i++){
			long beginTime = System.currentTimeMillis();
			Connection conn = DBConnManager.getInstance().getConnection();
			try{
				PreparedStatement statement = conn.prepareStatement("select * from blog");
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					
				}
			} catch(SQLException e){
				e.printStackTrace();
			}finally {
				try{
					conn.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("第" + (i+1) + "次用时：" + (endTime - beginTime));
		}
		
		//不使用连接池
		/*for(int i=0; i < 20; i++){
			long beginTime = System.currentTimeMillis();
			MysqlDataSource mds = new MysqlDataSource();
			mds.setURL("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
			mds.setUser("root");
			Connection conn = mds.getConnection();
			try{
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement("select * from blog");
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					
				}
			} catch(SQLException e){
				e.printStackTrace();
			}finally {
				try{
					conn.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("第" + (i+1) + "次用时：" + (endTime - beginTime));
		}*/
		
	}
}
