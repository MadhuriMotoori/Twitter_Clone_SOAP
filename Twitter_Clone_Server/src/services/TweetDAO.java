package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TweetDAO {

	public String tweet(String tweet, int id) {
		DBConnector connection = new DBConnector();
		String insertquery = "insert into tweets (tweet, userid) values ('" + tweet + "'," +  id +")";
		System.out.println(insertquery);
		
		int result;
		try {
			result = connection.getConnection().createStatement().executeUpdate(insertquery);
			
				if(result > 0)
					return "success";
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		connection.close();
		return "failure";
	}
	
	public ArrayList<TweetModel> getUserTweets(int id) {
		ArrayList<TweetModel> tweets = new ArrayList<TweetModel>();
		
		String tweetsQuery  = "select users.username , tweets.tweet, users.firstname , tweets.id, (tweets.tweetdate) as date from users inner join tweets on  " +
				"users.id = tweets.userid and users.id in (select followingUserid from following where userid=" + 
				id + ") UNION All select username, tweet, firstname, tweets.id, (tweets.tweetdate) as date from tweets, users where users.id= tweets.userid and users.id = " + id;
		System.out.println(tweetsQuery);
		ResultSet rs = null;

		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(tweetsQuery);

			while(rs.next()){
				TweetModel tweet = new TweetModel();
				tweet.setUsername(rs.getString("username"));
				tweet.setTweetId(rs.getInt("id"));
				tweet.setTweet(rs.getString("tweet"));
				tweet.setFirstname(rs.getString("firstname"));
				tweet.setTweetDate(rs.getTimestamp("date").getTime());
				
				tweets.add(tweet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return tweets;
	}
	
	
	public ArrayList<RetweetModel> getUserReTweets(int id) {
		ArrayList<RetweetModel> retweets = new ArrayList<RetweetModel>();
		
		
		String retweetsQuery = "select (r.username) as retweetUsername, (r.firstname) as retweetfirstname, r.retweetcomment, u.username, "+
				"u.firstname, u.tweet, u.date, r.retweetdate from (select username, firstname , tweet, (tweets.id) as tweetid, (tweets.tweetdate) as date from tweets inner join  users on users.id= tweets.userid) as u " +
				" inner join  (select username, firstname, retweetcomment, tweetid, retweetdate from retweets inner join users on users.id=retweets.userid and users.id in " +
						"(select followingUserid from following where userid=" + id + "  UNION select id from users where id= " + id+ ")) as r on r.tweetid = u.tweetid;";
		System.out.println(retweetsQuery);
		ResultSet rs = null;

		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(retweetsQuery);

			while(rs.next()){
				RetweetModel retweet = new RetweetModel();
				retweet.setRetweetUsername(rs.getString("retweetUsername"));
				retweet.setRetweetFirstname(rs.getString("retweetfirstname"));
				retweet.setRetweetComment(rs.getString("retweetcomment"));
				retweet.setUsername(rs.getString("username"));
				retweet.setFirstname(rs.getString("firstname"));
				retweet.setTweet(rs.getString("tweet"));
				Date date = rs.getTimestamp("date");
			 //   System.out.println("values are :" +  date.getTime());
				retweet.setTweetDate(rs.getTimestamp("date").getTime());
				retweet.setRetweetDate(rs.getTimestamp("retweetdate").getTime());

				retweets.add(retweet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return retweets;
	}
	
	public ArrayList<TweetModel> getTweetsByHashTag(String search) {
		ArrayList<TweetModel> tweets = new ArrayList<TweetModel>();
		
		
		String tweetsQuery  = "select tweets.tweet, users.firstname, users.username, tweets.id, (tweets.tweetdate) as date from tweets inner join users on "
				+ " tweets.userid=users.id where (tweet like '% " + search + " %' or tweet like '" + search
				+ " %' or tweet like '%" + search + "');";
		System.out.println(tweetsQuery);
		ResultSet rs = null;

		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(tweetsQuery);

			while(rs.next()){
				TweetModel tweet = new TweetModel();
				tweet.setUsername(rs.getString("username"));
				tweet.setTweetId(rs.getInt("id"));
				tweet.setTweet(rs.getString("tweet"));
				tweet.setFirstname(rs.getString("firstname"));
				tweet.setTweetDate(rs.getDate("date").getTime());
				
				tweets.add(tweet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return tweets;
	}
	
	
	public ArrayList<RetweetModel> getReTweetsByHashTag(String search) {
		ArrayList<RetweetModel> retweets = new ArrayList<RetweetModel>();
		
		
		String retweetsQuery = "select (r.username) as retweetUsername, (r.firstname) as retweetfirstname, r.retweetcomment, u.username, u.firstname,"
				+ " u.tweet, u.date, r.retweetdate from (select username, firstname , tweet, (tweets.id) as tweetid,"
				+ " (tweets.tweetdate) as date from tweets inner join  users on users.id= tweets.userid) as u  "
				+ "inner join  (select username, firstname, retweetcomment, tweetid, retweetdate from retweets"
				+ " inner join users on users.id=retweets.userid where (retweetcomment like '% " + search
				+ " %' or retweetcomment like '" + search + " %' or retweetcomment like '%"   + search
				+ "'))" + " as r on r.tweetid = u.tweetid  ;";
		System.out.println(retweetsQuery);
		ResultSet rs = null;

		DBConnector connection = new DBConnector();
		try {
			rs= connection.getConnection().createStatement().executeQuery(retweetsQuery);

			while(rs.next()){
				RetweetModel retweet = new RetweetModel();
				retweet.setRetweetUsername(rs.getString("retweetUsername"));
				retweet.setRetweetFirstname(rs.getString("retweetfirstname"));
				retweet.setRetweetComment(rs.getString("retweetcomment"));
				retweet.setUsername(rs.getString("username"));
				retweet.setFirstname(rs.getString("firstname"));
				retweet.setTweet(rs.getString("tweet"));
				retweet.setTweetDate(rs.getDate("date").getTime());
				retweet.setRetweetDate(rs.getDate("retweetdate").getTime());

				retweets.add(retweet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		connection.close();
		return retweets;
	}
	
	public String retweet(String retweetText, int userid, int tweetId ) {
		DBConnector connection = new DBConnector();
		String insertquery = "insert into retweets (userid, tweetid, retweetcomment) values ("
				+ userid + "," + tweetId + ",'" + retweetText + "');";
		System.out.println(insertquery);
		
		int result;
		try {
			result = connection.getConnection().createStatement().executeUpdate(insertquery);
			
				if(result > 0)
					return "success";
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return "failure";
	}
}
