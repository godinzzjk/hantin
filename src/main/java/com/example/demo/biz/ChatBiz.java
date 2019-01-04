package com.example.demo.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IChatDao;
import com.example.demo.pojo.User;

@Service("ChatBiz")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class ChatBiz {

	
	@Autowired
	private IChatDao chatdao;
	
	
	public User login(String userName,String userPwd) {
		return chatdao.login(userName, userPwd);
	}
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void modifyStatus(Integer status,Integer id) {
		chatdao.modifyStatus(status, id);
	}
	
	
}
