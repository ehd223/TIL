package org.client;

import org.frame.Dao;

public class UserMariaDao implements Dao{
	public UserMariaDao() {
		System.out.println("MariaDao Constructed");
	}
	
	public void insert() {
		System.out.println("Maria Inserted");
	}
}
