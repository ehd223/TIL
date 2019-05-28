package db;

//About User

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User("james", "id101", "pwd01");
		Biz biz = new UserBiz("192.168.0.1");
		System.out.println();
		biz.register(user);
		System.out.println();
		biz.remove("id01");
		System.out.println();
		
		User dbuser = null;
		dbuser = (User)biz.get("id01");
		System.out.println(dbuser);
	}

}
