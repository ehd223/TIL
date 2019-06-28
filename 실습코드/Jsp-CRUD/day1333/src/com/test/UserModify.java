package com.test;

import com.user.UserBiz;

import com.frame.Biz;
import com.vo.User;

public class UserModify {

	public static void main(String[] args) {

		Biz<String, User> biz = new UserBiz();
		User user = new User("ID78", "7878", "yourman");
		try {
			biz.modify(user);
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

}

