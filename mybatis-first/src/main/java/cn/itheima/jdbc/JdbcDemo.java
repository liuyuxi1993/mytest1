package cn.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDemo {

	public static void main(String[] args)  {
		Connection conn=null;
		PreparedStatement psmt =null;
		ResultSet rs=null;
		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.创建数据库创建对象
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bet_proj",
					"root", "root");
			//3.定义sql
			String sql="select * from user where id=?";
			//4.创建Statement语句对象
			psmt = conn.prepareStatement(sql);
			//5.设置参数
			psmt.setInt(1, 24);
			//6.执行
			rs = psmt.executeQuery();
			//7.处理结果集合
			while(rs.next()) {
				System.out.println("用户id:"+rs.getInt("id")+",用户名："+rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//8.释放资源
			try {
				if(rs !=null) rs.close();
				if(psmt != null) psmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
