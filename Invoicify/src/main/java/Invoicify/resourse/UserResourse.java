package Invoicify.resourse;



import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Invoicify.DTO.UserDTO;
import Invoicify.Services.UserServices;
import Invoicify.entity.HttpResponse;
import Invoicify.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/user")
@RequiredArgsConstructor
public class UserResourse {
	private final UserServices userService;
	
	@PostMapping("/register")
	public ResponseEntity<HttpResponse> saveUser(@RequestBody
			@Valid User user){
	UserDTO userDto=userService.createUser(user);
		return  ResponseEntity.created(getUri()).body(
				HttpResponse.builder()
				.timeStamp(now().toString())
				.data(of("user",userDto))
				.message("user Created")
				.status(CREATED)
				.statusCode(CREATED.value())
				.build());
				
	}

	private URI getUri() {
		
		return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
	}

	

}
