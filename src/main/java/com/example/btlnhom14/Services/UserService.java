package com.example.btlnhom14.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.btlnhom14.Entities.User;
import com.example.btlnhom14.Repositories.UserRepository;
import com.example.btlnhom14.Entities.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	public ResponseEntity<?> deleteUserById(Long id){
		
		User user = userRepository.findById(id).orElse(null);
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay nguoi dung");
		}
		userRepository.delete(user);
		return ResponseEntity.status(HttpStatus.OK).body("Da xoa thanh cong nguoi dung");
	}
	
	public ResponseEntity<?> findUserByUserName(String username){
		
		User user = userRepository.findByUsername(username).orElseThrow();
		
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<?> getAllUsers(){
		
        List<User> userList = userRepository.findByRoleContaining("USER");
        List<UserDTO> DtoList = userList.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(user.getUserId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setRole(user.getRole());

                    return userDTO;
                })
                .toList();

        return ResponseEntity.ok(DtoList);
	}
	
	public ResponseEntity<?> getAllAdmins() {
        
        List<User> userList = userRepository.findByRoleContaining("ADMIN");
        List<UserDTO> DtoList = userList.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(user.getUserId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setRole(user.getRole());

                    return userDTO;
                })
                .toList();

        return ResponseEntity.ok(DtoList);
	}
	
	public ResponseEntity<?> editUser(Long id, String newPassWord){
		
		User user = userRepository.findById(id).orElseThrow();
		user.setPassword(newPassWord);
		userRepository.save(user);
		
		return ResponseEntity.ok(user);
	}
	
	 public ResponseEntity<?> getAllUsersAdmins() {
	        Pageable paging = PageRequest.of(0, 3, Sort.by("username"));

	        Page<User> userList = userRepository.findAll(paging);

	        return ResponseEntity.ok(userList);
	}
	 
}
