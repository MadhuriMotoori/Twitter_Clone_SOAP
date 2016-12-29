package services;

import lib.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class User {

	@WebMethod
	public String register(String username, String email, String password, String firstname, String lastname,
			String gender, String dob) {

		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();

		if (userDAO.userNameExists(username)) {
			jsonObject.put("statusCode", 401);
			jsonObject.put("error", "Username Exists");
		} else {
			if (userDAO.emailExists(email)) {
				jsonObject.put("statusCode", 401);
				jsonObject.put("error", "Email Exists");
			} else {
				String result = userDAO.signUp(email, username, password, firstname, lastname, gender, dob);
				if (result == "success") {
					UserModel user = userDAO.getUserDetails(email, password);
					jsonObject.put("statusCode", 200);
					jsonObject.put("email", user.getEmail());
					jsonObject.put("username", user.getUserName());
					jsonObject.put("id", user.getId());
					jsonObject.put("firstname", user.getFirstName());
				} else {
					jsonObject.put("statusCode", 201);
					jsonObject.put("error", "Database error");
				}
			}
		}
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}

	@WebMethod
	public String login(String email) {

		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
	

		UserModel user = userDAO.getUserDetails(email);
		if (user != null) {
			jsonObject.put("statusCode", 200);
			jsonObject.put("email", user.getEmail());
			jsonObject.put("username", user.getUserName());
			jsonObject.put("password", user.getpassword());
			jsonObject.put("id", user.getId());
			jsonObject.put("firstname", user.getFirstName());
		} else {
			jsonObject.put("statusCode", 401);
			jsonObject.put("error", "Email Error");
		}
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	@WebMethod
	public String tweet(String tweet, int id){	
		TweetDAO tweetDAO = new TweetDAO();
		JSONObject jsonObject = new JSONObject();
		String result = tweetDAO.tweet(tweet, id);
		
		if (result == "success") {
			jsonObject.put("statusCode", 200);
		} else {
			jsonObject.put("statusCode", 401);
		}
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();	
	}
	
	@WebMethod
	public String showtweets(int id) {
		TweetDAO tweetDAO = new TweetDAO();
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
		
		UserModel user = new UserModel();
		
		ArrayList<TweetModel> tweets = tweetDAO.getUserTweets(id);
		int followingCount = userDAO.getFollowingCount(id);
		int followerCount = userDAO.getFollowerCount(id);
		int tweetCount = userDAO.getTweetCount(id) + userDAO.getReTweetCount(id);
		ArrayList<RetweetModel> retweets = tweetDAO.getUserReTweets(id);
		UserModel userUpdateInfo = userDAO.getUserUpdateInfo(id);
		
		jsonObject.put("statusCode", 200);
		jsonObject.put("tweets", tweets);
		jsonObject.put("following", followingCount);
		jsonObject.put("usertweet", tweetCount);
		jsonObject.put("followers", followerCount);
		jsonObject.put("retweets", retweets);
		jsonObject.put("dob", userUpdateInfo.getDate());
		jsonObject.put("location", userUpdateInfo.getLocation());
		System.out.println("value " + userUpdateInfo.getPhoneNumber());
		jsonObject.put("phonenumber", userUpdateInfo.getPhoneNumber());
	
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	@WebMethod
	public String followUserMainPage(String username){
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
	
		UserModel user = userDAO.getUserDetailsByUsername(username);
		
		if(user!=null){
			jsonObject.put("statusCode", 200);
			jsonObject.put("email", user.getEmail());
			jsonObject.put("username", user.getUserName());
			jsonObject.put("password", user.getpassword());
			jsonObject.put("id", user.getId());
			jsonObject.put("firstname", user.getFirstName());
		} else {
			jsonObject.put("statusCode", 401);
		}

		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	@WebMethod
	public String followUser(String username, int id){
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
	
		UserModel user = userDAO.getUserDetailsByUsername(username);
		if(user!= null){
			String result =  userDAO.followUser(id, user.getId());
			if(result == "success"){
				jsonObject.put("statusCode", 200);
			} else {
				jsonObject.put("statusCode", 201);
			}
		} else {
			jsonObject.put("statusCode", 401);
		}
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}

	@WebMethod
	public String followUserDetails(String username, int id, int fId) {
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
	
		UserModel user = userDAO.getUserDetailsByUsername(username);
		boolean tofollow = userDAO.isFollowing(id, fId);
		jsonObject.put("statusCode", 200);
		jsonObject.put("follow", tofollow);
		jsonObject.put("username", user.getUserName());
		jsonObject.put("firstname", user.getFirstName());
		
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	
	@WebMethod
	public String hashtagtweets(String search) {
		TweetDAO tweetDAO = new TweetDAO();
		JSONObject jsonObject = new JSONObject();
		
		ArrayList<TweetModel> tweets = tweetDAO.getTweetsByHashTag(search);
		ArrayList<RetweetModel> retweets = tweetDAO.getReTweetsByHashTag(search);

		jsonObject.put("statusCode", 200);
		jsonObject.put("tweets", tweets);
		jsonObject.put("retweets", retweets);
		
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	@WebMethod
	public String retweet(String retweetText, int userid, int tweetId ) {
		TweetDAO tweetDAO = new TweetDAO();
		JSONObject jsonObject = new JSONObject();
		String result = tweetDAO.retweet(retweetText, userid, tweetId);
		
		if (result == "success") {
			jsonObject.put("statusCode", 200);
		} else {
			jsonObject.put("statusCode", 401);
		}
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();	
	}
	
	@WebMethod
	public String followingList(int user_id){
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
		
		ArrayList<UserModel>  followingList = userDAO.getFollowingList(user_id);
		int followingCount = userDAO.getFollowingCount(user_id);
		int followerCount = userDAO.getFollowerCount(user_id);
		int tweetCount = userDAO.getTweetCount(user_id) +  userDAO.getReTweetCount(user_id);;
		
		jsonObject.put("statusCode", 200);
		jsonObject.put("followingList", followingList);
		jsonObject.put("following", followingCount);
		jsonObject.put("usertweet", tweetCount);
		jsonObject.put("followers", followerCount);

		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	@WebMethod
	public String followersList(int user_id){
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
		
		ArrayList<UserModel>  followersList = userDAO.getFollowersList(user_id);
		int followingCount = userDAO.getFollowingCount(user_id);
		int followerCount = userDAO.getFollowerCount(user_id);
		int tweetCount = userDAO.getTweetCount(user_id) + userDAO.getReTweetCount(user_id);;
		
		jsonObject.put("statusCode", 200);
		jsonObject.put("followersList", followersList);
		jsonObject.put("following", followingCount);
		jsonObject.put("usertweet", tweetCount);
		jsonObject.put("followers", followerCount);

		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();
	}
	
	@WebMethod
	public String profileupdate(int user_id, String location, int phonenumber){
		UserDAO userDAO = new UserDAO();
		JSONObject jsonObject = new JSONObject();
		String result = userDAO.updateProfile(user_id, location, phonenumber);
		
		if (result == "success") {
			jsonObject.put("statusCode", 200);
		} else {
			jsonObject.put("statusCode", 401);
		}
		System.out.println("JSON object" + jsonObject.toString());
		return jsonObject.toString();	
	}
}
