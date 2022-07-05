package com.dp.spring.template.controllers.authentication;

import com.dp.spring.template.controllers.base.BaseController;
import com.dp.spring.template.dtos.login.LoginDto;
import com.dp.spring.template.dtos.login.SignupDto;
import com.dp.spring.template.models.role.Role;
import com.dp.spring.template.models.user.User;
import com.dp.spring.template.responses.LoginResponse;
import com.dp.spring.template.services.implement.role.RoleServiceImpl;
import com.dp.spring.template.services.implement.user.UserDetailsImpl;
import com.dp.spring.template.services.implement.user.UserServiceImpl;
import com.dp.spring.template.utils.Constants;
import com.dp.spring.template.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*@CrossOrigin(origins = "*", maxAge = 3600)*/
@RestController
@RequestMapping("/authentication")
public class AuthenticationController extends BaseController {
	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	UserServiceImpl userService;

	@Autowired
	RoleServiceImpl roleService;

	@Autowired
    PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new LoginResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signUpRequest) {
	    // Validate username & email
        userService.validateUserByName(signUpRequest.getUsername());
        userService.validateUserByEmail(signUpRequest.getEmail());

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleService.findByName(Constants.RoleSecurity.ROLE_USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case Constants.RoleName.ADMIN:
					Role adminRole = roleService.findByName(Constants.RoleSecurity.ROLE_ADMIN);
					roles.add(adminRole);
					break;
				case Constants.RoleName.MODERATOR:
					Role modRole = roleService.findByName(Constants.RoleSecurity.ROLE_MODERATOR);
					roles.add(modRole);
					break;
				default:
					Role userRole = roleService.findByName(Constants.RoleSecurity.ROLE_USER);
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userService.create(user);

		return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
	}
}
