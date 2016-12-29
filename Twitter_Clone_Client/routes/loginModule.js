var ejs = require("ejs");
var bcrypt = require('bcrypt');
var soap = require('soap');
var baseURL = "http://localhost:8080/Twitter/services";
//Redirects to the homepage
exports.redirectToHomepage = function(req,res)
{
	if(req.session.user) {
		console.log("User entered to home page");
		res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
		res.render("userMainPage");
		//res.redirect('/loginPagetweets');
	} else {
		res.redirect('/loginPage');
	}

};



function login(req, res){	
	var email = req.param("email");
	var password = req.param("password");
	
	
	if(email!= undefined && password != undefined) {
		
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {email: email};
		  soap.createClient(url,option, function(err, client) {
		      client.login(args, function(err, result) {
		    	  if(result.loginReturn){
		    		  var result = JSON.parse(result.loginReturn);
			    	  if(result.statusCode == 200){
							var passwordhash = result.password; 
							var found = bcrypt.compareSync(password, passwordhash);
							if(found) {
								req.session.user = {email : result.email, username: result.username, id: result.id
										, firstname: result.firstname};
								res.send({"statusCode" : 200});						
							} else {
								console.log("Password is incorrect, please verify");
								json_responses = {"statusCode" : 401,
										   "error" : "Password Error"};
								res.send(json_responses);						
							}
		    	  }
		    	 
		    	  } else {
		    		  res.send(result);
		    	  }
		      });
		  });
	}  else {
		res.send({"statusCode" : 401, "error" : "Values not defined"});	
	}
}

//Logout the user - invalidate the session
exports.logout = function(req,res)
{
	req.session.destroy();
	res.redirect('/loginPage');
};

exports.login=login;
