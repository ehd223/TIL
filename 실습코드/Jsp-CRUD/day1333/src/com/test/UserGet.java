package com.test;

import com.user.UserBiz;

import com.frame.Biz;
import com.vo.User;

public class UserGet {

	public static void main(String[] args) {
	
		Biz<String, User> biz = new UserBiz();
		try {
			System.out.println(biz.get("id67"));
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;

	}

}
