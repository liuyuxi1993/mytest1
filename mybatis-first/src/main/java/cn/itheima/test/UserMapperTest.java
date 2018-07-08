package cn.itheima.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itheima.mapper.UserMapper;
import cn.itheima.po.User;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory=null;
	
	@Before
	public void init() throws IOException {
		//1.加载主配置文件sqlMapConfig.xml
		InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		
		//2.读取配置文件
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		sqlSessionFactory = builder.build(inputStream);
	}
	
	@Test
	public void queryUserByTest() {
		//1.创建sqlSession对象
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		//使用sqlSession对象，获取mapper代理对象
		/**
		 * getMapper方法：获取mapper代理对象
		 * 参数：
		 *    type:被代理接口的字节码
		 */
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		System.out.println(mapper.getClass().getName());
		
		//3.使用mapper代理对象，调用方法执行
		User user = mapper.queryUserById(3);
		System.out.println(user);
		
		//4.释放资源
		sqlSession.close();
	}
	
	@Test
	public void queryUserByAnnotationTest() {
		//1.创建sqlSession对象
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		//使用sqlSession对象，获取mapper代理对象
		/**
		 * getMapper方法：获取mapper代理对象
		 * 参数：
		 *    type:被代理接口的字节码
		 */
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//System.out.println(mapper.getClass().getName());
		
		//3.使用mapper代理对象，调用方法执行
		User user = mapper.queryUserById(3);
		System.out.println(user);
		
		//4.释放资源
		sqlSession.close();
	}
	@Test
	public void insertUserTest() {
		//1.创建sqlSession对象
		SqlSession sqlSession=this.sqlSessionFactory.openSession();
		//2.使用sqlSession对象，获取mapper代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//创建用户对象
		User user=new User();
		user.setUsername("天机老人");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("明朝人");
		
		mapper.insertUser(user);
		
		sqlSession.commit();
		
		//释放资源
		sqlSession.close();
		
	}
	
}
