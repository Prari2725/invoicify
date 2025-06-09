package Invoicify.Services.Implementation;

import org.springframework.stereotype.Service;

import Invoicify.DTO.UserDTO;
import Invoicify.DTOMapper.UserDTOMapper;
import Invoicify.Services.UserServices;
import Invoicify.entity.User;
import Invoicify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
	private final UserRepository <User> userRepository;

	@Override
	public UserDTO createUser(User user) {
		
		return  UserDTOMapper.fromUser(userRepository.create(user));
	}

	

	


	
}
