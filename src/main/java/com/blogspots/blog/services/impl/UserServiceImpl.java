package com.blogspots.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogspots.blog.entity.User;
import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.UserDto;
import com.blogspots.blog.repository.UserRepository;
import com.blogspots.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	public UserDto createUser(UserDto dto) {
		User u=this.dtoToUser(dto);
		User user=userRepository.save(u);
		return this.userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) throws ResourceNotFoundException {
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found With Given Id"+userId));
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		
		 User updatedUser=userRepository.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(int userId) throws ResourceNotFoundException {
		User u=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("No Resource Present with given Id"+userId));
		return this.userToDto(u);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepository.findAll();
		List<UserDto>dtousers=  users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return dtousers;
	}

	@Override
	public void deleteUser(int userId) throws ResourceNotFoundException {
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Entry Not found with id"+userId));
		this.userRepository.delete(user);
	}
	
	public User dtoToUser(UserDto dto) {
		User user=mapper.map(dto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto dto=mapper.map(user, UserDto.class);
		return dto;
	}

}
