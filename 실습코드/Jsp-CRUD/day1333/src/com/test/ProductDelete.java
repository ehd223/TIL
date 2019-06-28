package com.test;

import com.product.ProductBiz;

import com.frame.Biz;
import com.vo.Product;

public class ProductDelete {
	public static void main(String[] args) {

		Biz<Integer, Product> biz = new ProductBiz();

		try {
			biz.remove(100);
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

}
