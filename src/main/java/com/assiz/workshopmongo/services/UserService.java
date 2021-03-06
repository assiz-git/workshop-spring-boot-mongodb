package com.assiz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assiz.workshopmongo.domain.User;
import com.assiz.workshopmongo.dto.UserDTO;
import com.assiz.workshopmongo.repository.UserRepository;
import com.assiz.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User userUpdate) {
		User user = findById(userUpdate.getId());
		updateData(user, userUpdate);
		return userRepository.save(user);
	}

	public User fromDto(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	private void updateData(User user, User userUpdate) {
		user.setName(userUpdate.getName());
		user.setEmail(userUpdate.getEmail());
	}

}
