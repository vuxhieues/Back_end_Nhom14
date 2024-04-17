package com.example.btlnhom14.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private final AuthService authService;
	
	@PostMapping("/signup")
	private ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request){
		return authService.signUp(request);
	}
	
	@PostMapping("/login")
	private ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
		return authService.authenticate(request);
	}
	
	@PostMapping("/signup-admin")
	private ResponseEntity<AuthResponse> authenticate(@RequestBody SignUpRequest request){
		return authService.signUpAdmin(request);
	}
}
