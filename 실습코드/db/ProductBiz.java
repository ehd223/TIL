package db;

public class ProductBiz extends Biz {
	
	Dao dao;

	@Override
	public void register(Object obj) {
		// TODO Auto-generated method stub
		dao = new ProductDao("127.0.0.1:3306");
		dao.insert(obj);
		
	}

	@Override
	public void remove(Object obj) {
		dao.delete(obj);
	}

	@Override
	public Object get(Object obj) {		
		// obj를 id로 입력받아 DB에 조회.
		
		String str = (String) obj;
		Product product = (Product)dao.select(str);
		
		return product;
	}

}
