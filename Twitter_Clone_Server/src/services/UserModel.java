package services;

import java.util.ArrayList;
import java.util.Date;

public class UserModel {

	private int	id;
	private String firstName;
	private String lastName;
	private Date date;
	private String gender;
	private Date joindate;
	private String email;
	private String location;
	private int phonenumber;
	private String username;
	private String password;
	private ArrayList<TweetModel>	tweets;
	
	public String getpassword() {
		return password;
	}

	/**
	 * @return the tweets
	 */
	public ArrayList<TweetModel> getTweets() {
		return tweets;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(ArrayList<TweetModel> tweets) {
		this.tweets = tweets;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	
	public int getPhoneNumber() {
		return phonenumber;
	}

	public void setPhoneNumber(int number) {
		this.phonenumber = number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * @return the userID
	 */
	public int getId () {
		return id;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setId (int userId ) {
		this.id = userId;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName () {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName ( String firstName ) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the username
	 */
	public String getUserName () {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUserName ( String username ) {
		this.username = username;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName () {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName ( String lastName ) {
		this.lastName = lastName;
	}

	/**
	 * @return the date
	 */
	public Date getDate () {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate (Date date ) {
		this.date = date;
	}
	
	
	/**
	 * @return the date
	 */
	public Date getjoinDate () {
		return joindate;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setjoinDate (Date date ) {
		this.joindate = date;
	}

	/**
	 * @return the gender
	 */
	public String getGender () {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender ( String gender ) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
