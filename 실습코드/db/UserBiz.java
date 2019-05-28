package db;

public class UserBiz extends Biz {
	
	Dao dao;
	
	public UserBiz(String ip) {
		dao = new UserDao(ip);
	}

	@Override
	public void register(Object obj) {
		// Transaction start...
		
		dao.insert(obj);
		
		// Transaction end...
	}

	@Override
	public void remove(Object obj) {
		dao.delete(obj);
	}

	@Override
	public Object get(Object obj) {
		User user = null;
		
		// Transaction start ...
		user = (User)dao.select(obj);
		// Transaction end...
		
		return user;
	}

}
