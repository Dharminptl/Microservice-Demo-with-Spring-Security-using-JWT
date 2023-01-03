package com.example.ApiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {

//	@Autowired
//	private AuthenticationManager authenticationManager;

//	@Autowired
//	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping("/authenticate")
	public AuthenticateResponse authenticate(@RequestBody AuthenticateRequest request) {
//		authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String jwt = jwtUtil.generateToken(request.getUsername());

		return new AuthenticateResponse(jwt);
	}
	
	@GetMapping("/getStudentDetails")
	public String getStudentDetails(@RequestParam int id) {
		return "Test";
	}


}
