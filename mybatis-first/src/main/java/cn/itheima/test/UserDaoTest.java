package cn.itheima.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itheima.dao.UserDao;
import cn.itheima.dao.impl.UserDaoImpl;
import cn.itheima.po.User;

public class UserDaoTest {
	
	private  SqlSessionFactory sqlSessionFactory = null;
    @Before
	public void init() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		sqlSessionFactory = builder.build(inputStream);		
	}
	/**
	 * 测试根据用户id查询用户
	 */
    @Test
    public void queryUserByIdTest() {
    	//创建用户dao对象
    	UserDao userDao=new UserDaoImpl(sqlSessionFactory);
    	
    	User user=userDao.queryUserById(3);
    	System.out.println(user);
    }
	
	/**
	 * 测试新增
	 */
    @Test
    public void insert() {
    	//1.创建用户dao对象
    	UserDao userDao=new UserDaoImpl(sqlSessionFactory);
    	
    	//创建用户对象
    	User user =new User();
    	user.setUsername("阿飞");
    	user.setBirthday(new Date());
    	user.setSex("1");
    	user.setAddress("中华人民共和国公民");
    	
    	userDao.insertUSer(user);
    }
	
}
