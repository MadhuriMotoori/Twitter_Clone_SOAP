package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class UserDAO {

	public boolean userNameExists(String username){
		String query = "select * from users where username='" + username +"'";
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		boolean exists = false;
		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);
			if(rs.next()){
				exists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return exists;
	}
	
	
	public boolean emailExists(String email){
		String query = "select * from users where email='" + email +"'";
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		boolean exists = false;
		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);
			
			if(rs.next()){
				exists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return exists;
	}
	
	public String signUp(String email, String username, String password, String firstName, String lastName, String gender, String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {

			Date dateValue = formatter.parse(date);
			DBConnector connection = new DBConnector();
			String signup_details;
			signup_details = "insert into users (email, username, password, firstname,lastname, gender, date) values ('"+
			email + "','" + username + "','" + password + "','" + firstName + "','" + lastName + "','" + gender + 
			"','" + formatter.format(dateValue) + "');";
			System.out.println(signup_details);
			int result;
			try {
				result = connection.getConnection().createStatement().executeUpdate(signup_details);
				
					if(result > 0)
						return "success";
				
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
			connection.close();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "failure";
	}
	
	public UserModel getUserDetails(String email, String password){
		String query = "select * from users where email='" + email +"' and password='" +  password + "';";
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		UserModel user = null;
		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				user = new UserModel();
				user.setEmail(rs.getString("email"));
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setGender(rs.getString("gender"));
				user.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return user;
	}
	
	public UserModel getUserDetails(String email){
		String query = "select * from users where email='" + email +"';";
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		UserModel user = null;
		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				user = new UserModel();
				user.setEmail(rs.getString("email"));
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setGender(rs.getString("gender"));
				user.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return user;
	}
	
	public UserModel getUserDetailsByUsername(String username){
		String query = "select * from users where username='" + username +"';";
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		UserModel user = null;
		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				user = new UserModel();
				user.setEmail(rs.getString("email"));
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setGender(rs.getString("gender"));
				user.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return user;
	}
	
	
	public int getFollowingCount(int id){
		String query = "select count(followingUserid) as count from following where userid=" + id ;
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		DBConnector connection = new DBConnector();
		int count = 0;
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				count =  rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return count;
	}
	
	
	public int getFollowerCount(int id){
		String query = "select count(userid) as count from following where followingUserid=" + id ;
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		DBConnector connection = new DBConnector();
		int count = 0;
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				count =  rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return count;
	}
	
	
	public int getTweetCount(int id){
		String query = "select count(tweet) as count from tweets where userid=" + id ;
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		DBConnector connection = new DBConnector();
		int count = 0;
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				count =  rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return count;
	}
	
	public int getReTweetCount(int id){
		String query = "select count(*) as count from retweets where userid=" + id ;
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		DBConnector connection = new DBConnector();
		int count = 0;
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				count =  rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return count;
	}
	

	public UserModel getUserUpdateInfo(int id){
		String query = "select date, location, phonenumber from users where id = " + id ;
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		UserModel user = null;
		DBConnector connection = new DBConnector();
		int count = 0;
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);

			if(rs.next()){
				user = new UserModel();
				user.setDate(rs.getDate("date"));
				user.setLocation(rs.getString("location"));
				user.setPhoneNumber(rs.getInt("phonenumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return user;
	}
	
	public String followUser(int userId, int followingUserId ){
		String insertQuery = "insert into following(userid, followingUserid) values (" + userId + "," + followingUserId +")";
		DBConnector connection = new DBConnector();
		int result;
		try {
			result = connection.getConnection().createStatement().executeUpdate(insertQuery);
			
				if(result > 0)
					return "success";
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		connection.close();
		return "failure";
	}
	
	public boolean isFollowing(int userId, int followingUserId){
		String query = "select * from following where userid=" + userId + " and followingUserid=" + followingUserId + ";";
		System.out.println("Query is:" + query);
		
		ResultSet rs = null;
		boolean tofollow = true;
		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(query);
			if(rs.next()){
				tofollow = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return tofollow;
	}
	
	public ArrayList<UserModel> getFollowingList(int user_id){
		String followingUserQuery = "select username , firstname from users where id in (" +
		"select followingUserid from following where userid= "+ user_id + ");";
		
		ResultSet rs = null;
		DBConnector connection = new DBConnector();
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		
		try {
			rs= connection.getConnection().createStatement().executeQuery(followingUserQuery);
			while(rs.next()){
				UserModel user = new UserModel();
				user.setUserName(rs.getString("username"));
				user.setFirstName(rs.getString("firstname"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return users;
	}
	
	public ArrayList<UserModel> getFollowersList(int user_id){
		String followersUserQuery = "select username , firstname from users where id in (" +
				"select userid from following where followingUserid= "+ user_id + ");";
		
		ResultSet rs = null;
		DBConnector connection = new DBConnector();
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		
		try {
			rs= connection.getConnection().createStatement().executeQuery(followersUserQuery);
			while(rs.next()){
				UserModel user = new UserModel();
				user.setUserName(rs.getString("username"));
				user.setFirstName(rs.getString("firstname"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return users;
	}
	
	public String updateProfile(int user_id, String location, int phonenumber){
		String query = "update users set location ='" +  location + "', phonenumber ="+ phonenumber + " where id=" + user_id;
		DBConnector connection = new DBConnector();
		int result;
		try {
			result = connection.getConnection().createStatement().executeUpdate(query);
			if(result > 0)
				return "success";
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		connection.close();
		return "failure";
	}
	
}
