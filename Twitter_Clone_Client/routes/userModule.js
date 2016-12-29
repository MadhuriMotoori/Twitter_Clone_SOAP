var ejs = require("ejs");
var soap = require('soap');
var baseURL = "http://localhost:8080/Twitter/services";

//Redirects to the homepage
exports.followingPage = function(req,res)
{
	if(req.session.user) {
		console.log("User entered to following page");
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("userFollowingMainPage");
	} else {
		res.redirect('/loginPage');
	}
};

exports.followingList = function(req, res){
	var user_id = req.session.user.id;

	if(user_id != undefined && user_id != "") {
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {user_id: user_id};
		  soap.createClient(url,option, function(err, client) {
		      client.followingList(args, function(err, result) {
		    	  var result = JSON.parse(result.followingListReturn);
		    	  if(result.statusCode == 200){
						json_responses = {"statusCode" : 200,
								"followingList": result.followingList,
								"following" : result.following,
								"usertweet" : result.usertweet,
								"followers" : result.followers,
								"firstname" : req.session.user.firstname,
								"username"  : req.session.user.username
						   };
						
						res.send(json_responses);
		    	  } else {
		    		  res.send(result);
		    	  }
		      });
		  });
	} else {
		res.send({"statusCode" : 401});		
	}
}

exports.followersPage = function(req,res)
{
	if(req.session.user) {
		console.log("User entered to following page");
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("userFollowersMainPage");
	} else {
		res.redirect('/loginPage');
	}
};

exports.followersList = function(req, res){
	var user_id = req.session.user.id;

	if(user_id != undefined && user_id != "") {
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {user_id: user_id};
		  soap.createClient(url,option, function(err, client) {
		      client.followersList(args, function(err, result) {
		    	  var result = JSON.parse(result.followersListReturn);
		    	  if(result.statusCode == 200){
						json_responses = {"statusCode" : 200,
								"followingList": result.followersList,
								"following" : result.following,
								"usertweet" : result.usertweet,
								"followers" : result.followers,
								"firstname" : req.session.user.firstname,
								"username"  : req.session.user.username
						   };
						
						res.send(json_responses);
		    	  } else {
		    		  res.send(result);
		    	  }
		      });
		  });
	} else {
		res.send({"statusCode" : 401});		
	}	
}



exports.profilePage = function(req,res)
{
	if(req.session.user) {
		console.log("User entered to profile page");
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("userProfilePage");
	} else {
		res.redirect('/loginPage');
	}
};

exports.profileupdate = function(req, res){
	var user_id= req.session.user.id;
	var location = req.param("location");
	var phonenumber = req.param("phonenumber");
	
	if(user_id != undefined && user_id != "" && location != undefined && location != "" 
		&& phonenumber != undefined && phonenumber !=""){
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {user_id: user_id, location: location, phonenumber: phonenumber};
		  soap.createClient(url,option, function(err, client) {
		      client.profileupdate(args, function(err, result) {
		    	  var result = JSON.parse(result.profileupdateReturn);
		    	  res.send(result);  
		      });
		  });
	} else {
		res.send({"statusCode" : 401});	
	}
}