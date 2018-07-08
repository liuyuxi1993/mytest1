package cn.itheima.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.itheima.dao.UserDao;
import cn.itheima.po.User;

public class UserDaoImpl implements UserDao {

	//定义SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		super();
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 根据用户id查询用户
	 */
	public User queryUserById(Integer id) {
		//1.创建sqlSession
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		//2.使用sqlSession对象，调用方法执行
		Object user = sqlSession.selectOne("test.queryUserById",id);
		
		//3.释放资源
		sqlSession.close();
		
		return (User)user;
	}

	/**
	 * 新增用户
	 */
	public void insertUSer(User user) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession(true);
		
		sqlSession.insert("test.insertUser", user);
	}

}
