package cn.itheima.mapper;

import org.apache.ibatis.annotations.Select;

import cn.itheima.po.User;

public interface UserMapper {

	//1根据用户id查询用户
	@Select("select * from user where id =#{id}")
	User queryUserById(Integer id);
	
	//2. 新增一个用户
	void insertUser(User user);
}
