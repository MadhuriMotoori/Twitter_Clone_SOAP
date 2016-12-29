var ejs = require("ejs");
var bcrypt = require('bcrypt');
var soap = require('soap');
var baseURL = "http://localhost:8080/Twitter/services";

function register(req, res){
	var username = req.param("username");
	var email = req.param("email");
	var passwordValue = req.param("password");
	var password = bcrypt.hashSync(passwordValue, 10);
	var firstname = req.param("firstname");
	var lastname = req.param("lastname");
	var gender = req.param("gender");
	var dob = req.param("dob"); 
	
	if(username != undefined && email != undefined && password != undefined && firstname != undefined && lastname != undefined 
			&& gender != undefined && dob != undefined) {
		
		var option = {
				ignoredNamespaces : true	
			};
		 var url = baseURL+"/User?wsdl";
		  var args = {username: username, email: email, password: password, firstname: firstname, lastname: lastname,
				  gender: gender, dob: dob};
		  soap.createClient(url,option, function(err, client) {
		      client.register(args, function(err, result) {
		    	  var result = JSON.parse(result.registerReturn);
		    	  if(result.statusCode == 200){
		    		  req.session.user = {email : result.email, username: result.username, id: result.id, firstname: result.firstname};
		    		  res.send(result);
		    	  }else if(result.statusCode == 201){
		    		  res.redirect('/');
		    	  } else{
		    		  res.send(result);
		    	  }
		      });
		  });
	} else {
		res.send({"statusCode" : 401, "error" : "Values not defined"});		
	}
}

exports.register=register;