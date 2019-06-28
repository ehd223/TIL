package com.test;

import com.user.UserBiz;

import com.frame.Biz;
import com.vo.User;

public class UserDelete {
	public static void main(String[] args) {

		Biz<String, User> biz = new UserBiz();

		try {
			biz.remove("id67");
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

}
