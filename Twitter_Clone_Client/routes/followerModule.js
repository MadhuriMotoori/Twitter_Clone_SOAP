var ejs = require("ejs");
var soap = require('soap');
var baseURL = "http://localhost:8080/Twitter/services";

function followUserMainPage(req, res){
	var followUsername = req.params.user;
	var query = "select * from users where username='" + followUsername +"'";
	
	if(followUsername != undefined && followUsername !="") {
		
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {username: followUsername};
		  soap.createClient(url,option, function(err, client) {
		      client.followUserMainPage(args, function(err, result) {
		    	  var result = JSON.parse(result.followUserMainPageReturn);
		    	  if(result.statusCode == 200){
						req.session.follower = {email : result.email, username: result.username, id: result.id
								, firstname: result.firstname};
						ejs.renderFile('./views/followUserMainPage.ejs', {data: result}, function(err, result) {
							   // render on success
							   if (!err) {
							            res.end(result);
							   }
							   // render or error
							   else {
							            res.end('An error occurred');
							            console.log(err);
							   }
						   });
		    	  } else {
		    		  res.redirect('/homePage');
		    	  }
		      });
		  });
	} else {
		res.redirect('/homePage');
	}
}

function followUser(req, res){
	var tofollowUser = req.session.follower.username;
	var userId = req.session.user.id;
	
	if(tofollowUser != undefined && tofollowUser != "") {
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {username: tofollowUser, id: userId};
		  soap.createClient(url,option, function(err, client) {
		      client.followUser(args, function(err, result) {
		    	  var result = JSON.parse(result.followUserReturn);
		    	  if(result.statusCode == 200){
						req.session.follower = null;
						console.log("User is added into following list");
				
						json_responses = {"statusCode" : 200};
						res.send(json_responses);
		    	  } else if(result.statusCode == 201){
		    		  res.redirect('/userMainPage');
		    	  } else {
		    		  res.send({"statusCode" : 401});
		    	  }
		      });
		  });
	} else {
		res.send({"statusCode" : 401});
	}
}

function followUserDetails(req, res) {
	var tofollowUser = req.session.follower.username;
	var followingUserId = req.session.follower.id;
	var userId = req.session.user.id;
	
	if(tofollowUser != undefined && tofollowUser != "") {
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {username: tofollowUser, id: userId, fId: followingUserId};
		  soap.createClient(url,option, function(err, client) {
		      client.followUserDetails(args, function(err, result) {
		    	  var result = JSON.parse(result.followUserDetailsReturn);
		    	  if(result.statusCode == 200){
		    		  res.send(result)
		    	  } else {
		    		  res.send( {"statusCode" : 401});
		    	  }
		      });
		  });
	} else {
		res.send( {"statusCode" : 401});
	}
}

exports.followUserMainPage=followUserMainPage;
exports.followUser=followUser;
exports.followUserDetails=followUserDetails;