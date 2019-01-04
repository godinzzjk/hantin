package com.example.demo.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.biz.ChatBiz;
import com.example.demo.pojo.User;
import com.github.pagehelper.PageInfo;

@SuppressWarnings("all")
@RestController
@RequestMapping("/c/users")
public class UersAction {

	@Autowired
	private ChatBiz charbiz;

	@GetMapping("loginIn/{userName}/{userPwd}")
	public User loginIn(@PathVariable String userName, @PathVariable String userPwd,HttpSession session) {
		
		User user=charbiz.login(userName, userPwd);
		session.setAttribute("USERS", user);
		return user;
	}
	
	@GetMapping("GetSession")
	public User GetSession(HttpSession session) {
		User user=(User)session.getAttribute("USERS");
		return user;
	}
	
	/*@GetMapping("destry")
	public String destry(HttpSession session) {
		session.removeAttribute("USER");
		session.invalidate();// 会话销毁
		return "redirect:/view/login";
	}*/
	

}
