package com.test;

import com.user.UserBiz;

import com.frame.Biz;
import com.vo.User;

public class UserInsert {

	public static void main(String[] args) {

		User u = new User("id67", "pwd67", "이동영");
		Biz<String, User> biz = new UserBiz();

		try {
			biz.register(u);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

}
