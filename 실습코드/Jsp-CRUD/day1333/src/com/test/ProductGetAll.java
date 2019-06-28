package com.test;

import com.product.ProductBiz;

import com.frame.Biz;
import com.vo.Product;

public class ProductGetAll {

	public static void main(String[] args) {

		Biz<Integer, Product> biz = new ProductBiz();

		try {
			System.out.println(biz.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
