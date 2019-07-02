package org.client;

import org.frame.Dao;

public class UserOracleDao implements Dao{
	public UserOracleDao() {
		System.out.println("OracleDao Constructed");
	}
	
	public void insert() {
		System.out.println("Oracle Inserted");
	}
}
