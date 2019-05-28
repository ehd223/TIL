package db;

public class ProductDao extends Dao {
	
	public ProductDao() {
		
	}
	
	public ProductDao(String ip) {
		this.ip = ip;
	}

	@Override
	public void insert(Object obj) {
		connection();
		
		Product product = (Product) obj;
		System.out.println(product.getId() + " " + 
				product.getName() + " " +
				product.getPrice());
		
		close();
	}

	@Override
	public Object select(Object obj) {
		Product product = null;
		String str = (String) obj;
		
		connection();
		product = new Product(str, "notebook", 3500.0);
		close();
		
		return product;
	}

	@Override
	public void delete(Object obj) {
		// id를 obj로 받아 해당 Product 삭제
		String str = (String) obj;
		
		connection();
		System.out.println(str + ": Deleted..");
		close();

	}

}
