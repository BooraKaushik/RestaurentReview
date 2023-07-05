package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dao.RestaurentDAO;
import com.project.demo.model.Comment;
import com.project.demo.model.Restaurent;

@RestController
@RequestMapping("/api/v1")
public class RestaurentController {
	@Autowired
	private RestaurentDAO restaurentDao;
	
	@GetMapping("/restaurent")
	public List<Restaurent> getAllRestaurents() {
		return restaurentDao.findAll();
	}
	
	@GetMapping("/restaurent/{restaurentId}/comments")
	public List<Comment> getAllCommentsOfRestaurent(@RequestParam long restaurentId) {
//		return restaurentDao.findByRestaurent_ID(restaurentId);
		return null;
	}
	
	@PostMapping("/restaurent")
	public Restaurent createRestaurent(@RequestBody Restaurent restaurent) {
		return restaurentDao.save(restaurent);
	}

}
