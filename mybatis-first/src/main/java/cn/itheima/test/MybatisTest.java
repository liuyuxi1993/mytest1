package cn.itheima.test;
/**
 * @ClassName: MybatisTest 
 * @Description: mybatis入门程序测试
 * @author lyx
 * @date 2018年4月22日 上午8:38:30
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itheima.po.User;

public class MybatisTest {

	private SqlSessionFactory sqlSessionFactory=null;
	@Before
	public void init() throws IOException {
		//1.加载住配置文件sqlMapConfig.xml
				InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
				//2.读取配置文件内容
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				/**
				 * SqlSessionFactory对象：
				 *   1.它是mybatis框架的核心对象
				 *   2.它是线程安全的，一个应用中通常只需要一个即可
				 */
				sqlSessionFactory = builder.build(inputStream);
	}
	
	/**
	 * @Title: queryUserByIdTest 
	 * @Description: 测试根据用户id查询用户
	 * @throws IOException    设定文件 
	 */
	@Test
	public void queryUserByIdTest() throws IOException {
}
    /**
     * @Title: queryUserByNameTest 
     * @Description: 测试根据用户名称模糊查询用户 
     * @throws IOException    设定文件 
     */
	@Test
	public void queryUserByNameTest() throws IOException {
		//1.加载住配置文件sqlMapConfig.xml
		InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2.读取配置文件内容
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		/**
		 * SqlSessionFactory对象：
		 *   1.它是mybatis框架的核心对象
		 *   2.它是线程安全的，一个应用中通常只需要一个即可
		 */
		SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
		//3.使用SqlSessionFactory对象，创建SqlSession对象
		/**
		 * SqlSession对象
		 *  1.它相当于jdbc中的connection对象
		 *  2.它提供了操作数据库的CRUD方法
		 *  2.它是线程不安全的，每一个执行的方法，都需要创建自己的sqlSession
		 */
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//4.使用sqlSession对象，调用方法执行
		/**
		 * selectOne方法：查询单条记录
		 *   参数：
		 *     statement:执行的sql语句（名称空间+"."+sql语句的id）
		 *     parameter:传入的参数值
		 */
		List<Object> list = sqlSession.selectList("test.queryUserByName","' OR 1=1 OR '");
		for (Object o : list) {
			System.out.println(o);
		}
		//5.释放资源
		sqlSession.close();
	}
	
	@Test
	public void insertUserTest() {
		//1创建sqlSession对象
		SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
		//2.使用sqlSession对象，调用方法执行
		/**
		 * insert方法：新增记录
		 * statement:执行的sql语句（名称空间+"."+sql语句id）
		 * parameter:传入的参数值
		 */
		//创建对象用户
		User user=new User();
		user.setId(4);
		user.setUsername("林姑娘");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("明朝人");
		
		sqlSession.insert("test.insertUser",user);
		
		System.out.println(user);
		
		//手动提交事务
		/*sqlSession.commit();*/
		
		//3.释放资源
		sqlSession.close();
	}
	@Test
	public void updateUserTest() {
		//1创建sqlSession对象
		SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
		//2.使用sqlSession对象，调用方法执行
		
		//创建对象用户
		User user=new User();
		user.setId(8);
		user.setUsername("小李飞道道道");
		user.setSex("2");
		
		sqlSession.insert("test.updateUserById",user);
		
		
		//手动提交事务
		/*sqlSession.commit();*/
		
		//3.释放资源
		sqlSession.close();
	}
	@Test
	public void delete() {
		SqlSession salSession = this.sqlSessionFactory.openSession(true);
		salSession.delete("test.deleteUserById", 8);
		salSession.close();
	}
	
}
