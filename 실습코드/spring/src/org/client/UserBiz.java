package org.client;

import org.frame.Biz;
import org.frame.Dao;

public class UserBiz implements Biz{
	Dao dao1;
	Dao dao2;
	
	public UserBiz() {
		System.out.println("UserBiz Constructed");
	}
	
	public UserBiz(Dao dao1, Dao dao2) {
		this.dao1 = dao1;
		this.dao1 = dao2;
		System.out.println("UserBiz Constructed with " + dao1.getClass().getName()
							+ " and " + dao2.getClass().getName());
	}

	public Dao getDao1() {
		return dao1;
	}

	public void setDao1(Dao dao1) {
		this.dao1 = dao1;
	}

	public Dao getDao2() {
		return dao2;
	}

	public void setDao2(Dao dao2) {
		this.dao2 = dao2;
	}

	public void register() {
		System.out.println("UserBiz Register");
		
		this.dao1.insert();
		this.dao2.insert();
	}
}
