package services;

import java.util.Date;

public class RetweetModel {
	private String retweetUsername;
	private String retweetFirstname; 
	private String retweetComment;
	private String username;
	private String firstname;
	private String tweet;
	private long tweetDate;
	private long retweetDate;
	/**
	 * @return the retweetUsername
	 */
	public String getRetweetUsername() {
		return retweetUsername;
	}
	/**
	 * @param retweetUsername the retweetUsername to set
	 */
	public void setRetweetUsername(String retweetUsername) {
		this.retweetUsername = retweetUsername;
	}
	/**
	 * @return the retweetFirstname
	 */
	public String getRetweetFirstname() {
		return retweetFirstname;
	}
	/**
	 * @param retweetFirstname the retweetFirstname to set
	 */
	public void setRetweetFirstname(String retweetFirstname) {
		this.retweetFirstname = retweetFirstname;
	}
	/**
	 * @return the retweetComment
	 */
	public String getRetweetComment() {
		return retweetComment;
	}
	/**
	 * @param retweetComment the retweetComment to set
	 */
	public void setRetweetComment(String retweetComment) {
		this.retweetComment = retweetComment;
	}
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
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}
	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
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
	/**
	 * @return the retweetDate
	 */
	public long getRetweetDate() {
		return retweetDate;
	}
	/**
	 * @param retweetDate the retweetDate to set
	 */
	public void setRetweetDate(long retweetDate) {
		this.retweetDate = retweetDate;
	}
}
