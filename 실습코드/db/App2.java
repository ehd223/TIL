package db;

// About Product

public class App2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product product = new Product("p01", "pencil", 2000);
		Biz biz = null;
		
		biz = new ProductBiz();
		
		biz.register(product);
		
		System.out.println();
		
		Product np = (Product) biz.get("p02");
		System.out.println(np.toString());
		System.out.println();
		
		biz.remove(np.getId());

	}

}
