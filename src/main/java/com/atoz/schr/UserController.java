package com.atoz.schr;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.atoz.service.UserService;

@RestController
public class UserController {
		@Autowired
	    UserService userService;

		// list page
		@RequestMapping(value = "/users", method = RequestMethod.GET)
		public ResponseEntity<List<User>> showAllUsers() {

			List<User> users=  userService.findAll();
			
			Iterator<User> userList = users.iterator();
			while(userList.hasNext()){
				System.out.println("controller: findAll ::"+userList.next());
			}
			
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			
		}

		//-------------------Retrieve Single User--------------------------------------------------------
	    
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
	        System.out.println("Fetching User with id " + id);
	        User user = userService.findById(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	 
	     
	     
	    //-------------------Create a User--------------------------------------------------------
	     
	    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User ");
	 
	        userService.save(user);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	    
	     
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
	        System.out.println("Updating User " + id);
	         
	        User currentUser = userService.findById(id);
	         
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        System.out.println("Updating user with id:"+currentUser.toString());
	        System.out.println("Updating user with new user Id:"+user.toString());
	        currentUser.setAddress(user.getAddress());
	        currentUser.setEmail(user.getEmail());
	         
	        userService.Update(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	 
	    
	    
	    //------------------- Delete a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	 
	        User user = userService.findById(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        userService.delete(id);
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	 
	    
	

}
