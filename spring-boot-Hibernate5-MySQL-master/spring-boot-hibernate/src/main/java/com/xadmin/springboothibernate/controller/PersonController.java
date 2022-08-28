package com.xadmin.springboothibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xadmin.springboothibernate.dao.PersonDao;
import com.xadmin.springboothibernate.model.Person;

@RestController
@RequestMapping("/orm")
public class PersonController {

	@Autowired
	private PersonDao dao;

	@PostMapping("/savePerson")
	public String save(@RequestBody Person person) {
		dao.saveperson(person);
		return "saved successfully";
	}

	@GetMapping("/getAllPersons")
	public List<Person> getAllperson() {
		return dao.getpersons();

	}

}
