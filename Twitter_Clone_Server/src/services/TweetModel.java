package services;

import java.util.Date;

public class TweetModel {
	
	private String username;
	private String tweet;
	private String firstname;
	private int tweetId;
	private long tweetDate;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the tweeet
	 */
	public String getTweet() {
		return tweet;
	}
	/**
	 * @param tweeet the tweeet to set
	 */
	public void setTweet(String tweeet) {
		this.tweet = tweeet;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the tweetId
	 */
	public int getTweetId() {
		return tweetId;
	}
	/**
	 * @param tweetId the tweetId to set
	 */
	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}
	/**
	 * @return the tweetDate
	 */
	public long getTweetDate() {
		return tweetDate;
	}
	/**
	 * @param tweetDate the tweetDate to set
	 */
	public void setTweetDate(long tweetDate) {
		this.tweetDate = tweetDate;
	}

}
