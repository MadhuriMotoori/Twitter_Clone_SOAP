var ejs = require("ejs");
var soap = require('soap');
var baseURL = "http://localhost:8080/Twitter/services";

function tweet(req, res) {
	var user_id = req.session.user.id;
	var tweetText = req.param("tweet");

	if (tweetText != undefined && tweetText != "") {
		var option = {
			ignoredNamespaces : true
		};
		var url = baseURL + "/User?wsdl";
		var args = {
			tweet : tweetText,
			id : user_id
		};
		soap.createClient(url, option, function(err, client) {
			client.tweet(args, function(err, result) {
				var result = JSON.parse(result.tweetReturn);
				if (result.statusCode == 200) {
					res.redirect('/loginPageTweets');
				}
			});
		});
	} else {
		res.send({
			"statusCode" : 401
		});
	}
}

function showtweets(req, res) {
	var user_id = req.session.user.id;

	if (user_id != undefined && user_id != "") {
		var option = {
				ignoredNamespaces : true
			};
			var url = baseURL + "/User?wsdl";
			var args = {id : user_id};
			soap.createClient(url, option, function(err, client) {
				client.showtweets(args, function(err, result) {
					var result = JSON.parse(result.showtweetsReturn);
					json_responses = {
							"statusCode" : result.statusCode,
							"tweets" : result.tweets,
							"following" : result.following,
							"usertweet" : result.usertweet,
							"followers" : result.followers,
							"firstname" : req.session.user.firstname,
							"username" : req.session.user.username,
							"retweets" : result.retweets,
							"dob" : result.dob,
							"location" : result.location,
							"phonenumber" : result.phonenumber
						};
						res.send(json_responses);
				});
			});


	} else {
		res.send({
			"statusCode" : 401
		});
	}
}

function hashtagtweets(req, res) {
	var search = req.param("search");

	if (search != undefined && search != "") {
		
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {search: search};
		  soap.createClient(url,option, function(err, client) {
		      client.hashtagtweets(args, function(err, result) {
		    	  var result = JSON.parse(result.hashtagtweetsReturn);
		    	  if(result.statusCode == 200){
						json_responses = {
								"statusCode" : 200,
								"tweets" : result.tweets,
								"retweets" : result.retweets,
							};
							res.send(json_responses);
		    	  } 
		      });
		  });
	} else {
		res.send({ "statusCode" : 401});
	}
}

function retweet(req, res) {
	var retweetText = req.param("retweetText");
	var userid = req.session.user.id;
	var tweetId = req.param("retweetId");

	if (userid != undefined && userid != "") {
		var option = {
				ignoredNamespaces : true
			};
			var url = baseURL + "/User?wsdl";
			var args = {
				retweetText : retweetText,
				userid : userid,
				tweetId : tweetId
			};
			soap.createClient(url, option, function(err, client) {
				client.retweet(args, function(err, result) {
					var result = JSON.parse(result.retweetReturn);
					res.send(result);
				});
			});
	} else {	
		res.send(json_responses = {"statusCode" : 401});
	}
}
exports.tweet = tweet;
exports.showtweets = showtweets;
exports.hashtagtweets = hashtagtweets;
exports.retweet = retweet;