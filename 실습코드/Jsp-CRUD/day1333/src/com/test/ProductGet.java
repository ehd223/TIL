package com.test;

import com.product.ProductBiz;

import com.frame.Biz;
import com.vo.Product;

public class ProductGet {

	public static void main(String[] args) {
	
		Biz<Integer, Product> biz = new ProductBiz();
		try {
			System.out.println(biz.get(102));
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;

	}

}
