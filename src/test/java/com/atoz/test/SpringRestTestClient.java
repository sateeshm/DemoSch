package com.atoz.test;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.atoz.schr.User;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/";

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllUsers() {
		System.out.println("Testing listAllUsers API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI + "/users",
				List.class);

		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				System.out.println("User : id=" + map.get("id") + ", Name=" + map.get("name") + ", Address="
						+ map.get("Address") + ", email=" + map.get("email"));
				;
			}
		} else {
			System.out.println("No user exist----------");
		}
	}
	
	   /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setId(null);
        user.setName("Sarah2");
        user.setAddress("MO");
        user.setEmail("Sarah@test.com");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/add", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User();
        user.setId(1);
        user.setName("Sarah");
        user.setAddress("MD");
        user.setEmail("abc@test.com");
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }
 
    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/6");
    }
 
 
    /* DELETE */
    private static void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }

	public static void main(String args[]) {
		//listAllUsers();
		//createUser();
		deleteUser();
		//updateUser();
	}

}
