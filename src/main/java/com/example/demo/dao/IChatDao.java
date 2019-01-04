package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.User;

public interface IChatDao {

	//登录
	public User login(@Param("userName")String userName,@Param("userPwd")String userPwd);
	
	
	public void modifyStatus(@Param("status")Integer status,@Param("id")Integer id);
	
}
