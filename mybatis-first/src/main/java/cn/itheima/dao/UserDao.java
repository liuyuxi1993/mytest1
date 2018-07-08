package cn.itheima.dao;

import cn.itheima.po.User;

public interface UserDao {

	//1.根据用户id查询用户
	User queryUserById(Integer id);
	
	//2.新增一个用户
	void insertUSer(User user);
	
}
