package com.example.btlnhom14.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.btlnhom14.Services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

	@Autowired
	private final UserService userService;
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		return userService.deleteUserById(id);
	}
	
	@PutMapping("/edit/{userId}")
	public ResponseEntity<?> editUser(@PathVariable("userId") Long userId, @RequestBody String newPassWord) {
		newPassWord = newPassWord.replace("\"", "");
	    return userService.editUser(userId, newPassWord);
	}
	
	@GetMapping("/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }
	
	@GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }
	
	@GetMapping("/all-admins")
    public ResponseEntity<?> getAllAdmins() {
        return userService.getAllAdmins();
    }
	
	@GetMapping("/all-users-admins")
    public ResponseEntity<?> getAllUsersAdmins() {
        return userService.getAllUsersAdmins();
    }
}
