package com.assiz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assiz.workshopmongo.domain.Post;
import com.assiz.workshopmongo.repository.PostRepository;
import com.assiz.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository PostRepository;

	public List<Post> findAll() {
		return PostRepository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> Post = PostRepository.findById(id);
		return Post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public List<Post> findByTitle (String text) {
		return PostRepository.findByTitleContainingIgnoreCase(text);
	}
	
}
