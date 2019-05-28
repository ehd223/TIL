package db;

public class UserDao extends Dao {
	
	public UserDao() {
		
	}
	
	public UserDao(String ip) {
		super(ip);
	}

	@Override
	public void insert(Object obj) {
		connection();
		User user = (User) obj;
		System.out.println(user.getId() + " " + 
							user.getName() + " " +
							user.getPwd());
		
		close();
	}

	@Override
	public Object select(Object obj) {
		User user = null;
		String str = (String) obj;
		// DB에서 SELECT해서 데이터를 가지고 온다.
		connection();
		user = new User(str, "Tom", "pwd02");
		close();
		return user;
	}

	@Override
	public void delete(Object obj) {
		String str = (String) obj;
		connection();
		
		System.out.println(str+" Deleted...");
		close();
	}

}
