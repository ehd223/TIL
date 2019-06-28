package com.test;

import com.user.UserBiz;

import com.frame.Biz;
import com.vo.User;

public class UserGetAll {

	public static void main(String[] args) {

		Biz<String, User> biz = new UserBiz();

		try {
			System.out.println(biz.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
