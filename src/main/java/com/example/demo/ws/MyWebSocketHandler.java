package com.example.demo.ws;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;




//1、配置站点
@ServerEndpoint(value = "/ws/chat/{userName}")
@Component
public class MyWebSocketHandler {
	
	public static ApplicationContext ac;// 非常重要
	
	private final static Map<String, Session> userMap=new ConcurrentHashMap<String, Session>();
	
	private String userName;
	
	@OnOpen
	public void onopen(Session session,@PathParam("userName")String userName) {
		if(userMap.size()!=0) {
			for (Map.Entry<String, Session> user : userMap.entrySet()) {
				try {
					if(user.getKey()!=userName) {
						userMap.put(userName, session);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					continue;
				}
			}
		}else {
			userMap.put(userName, session);
		}
		this.userName=userName;
		
		//启用心跳程序
		/*new Thread() {
			public void run() {
				while(true) {
					try {
						try {
							session.getBasicRemote().sendText("服务器与数据库连接未中断!");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread.sleep(270000);
					} catch (InterruptedException e) {
						userSet.remove(session);
						break;
					}
				}
			}
		}.start();*/
		System.out.println("接收客户端【"+session.getId()+userName+"】连接");
	}
	
	
	@OnClose
	public void onclose(Session session) {
		try {
			for (Map.Entry<String, Session> user : userMap.entrySet()) {
				try {
					if(user.getKey()!=userName) {
						user.getValue().getBasicRemote().sendText("end%%"+userName);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					continue;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("客户端关闭");
		userMap.remove(userName);
	}
	
	@OnError
	public void onerror(Session session,Throwable e) {
		System.out.println("通讯异常");
		userMap.remove(userName);
	}
	
	public void sendAllUsers(String msg) {
		for (Map.Entry<String, Session> user : userMap.entrySet()) {
			try {
				if(user.getKey()!=userName) {
					user.getValue().getBasicRemote().sendText(userName+"%%"+msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				continue;
			}
		}
	}
//	
//	private void sendUsers(String name,String msg) {
//		try {
//			userMap.get(name).getBasicRemote().sendText(msg);
//		} catch (IOException e) {
//			return;
//		}
//	}
	
	//这个方法参数不能换位置
	@OnMessage
	public void onmessage(String msg,Session session) {
		System.out.println("服务器端和客户端通讯");
		System.out.println("接收客户端【"+session.getId()+userName+"】连接的消息"+msg);
		if("ping".equals(msg)) {
			try {
				for (Map.Entry<String, Session> user : userMap.entrySet()) {
					try {
							user.getValue().getBasicRemote().sendText("pong%%"+userName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						continue;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*}else if("end".equals(msg)) {
			try {
				for (Map.Entry<String, Session> user : userMap.entrySet()) {
					try {
							user.getValue().getBasicRemote().sendText("end%%"+userName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						continue;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}else {
			this.sendAllUsers(msg);
		}
		
	}
	
}
