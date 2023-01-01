package com.brillio.problem.statement1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.problem.statement1.model.People;
import com.brillio.problem.statement1.repository.PeopleRepository;

@Service
public class PeopleService {
	
	@Autowired
	PeopleRepository peopleRepository;
	
	public People newPerson(People person) {
		return peopleRepository.save(person);
	}
	
	public List<People> getPeople(){
		return peopleRepository.findAll();
	}
	
	public Optional<People> getPerson(Long id) {
		return peopleRepository.findById(id);
	}
	
	public People updatePerson(Long id, People editPerson) {
		People person = peopleRepository.findById(id).orElse(null);
		if(person!=null) {
			person.setFirstName(editPerson.getFirstName());
			person.setFirstName(editPerson.getFirstName());
			person.setLastName(editPerson.getLastName());
			person.setDateOfBirth(editPerson.getDateOfBirth());
			person.setAddress(editPerson.getAddress());
			person.setCity(editPerson.getCity());
			return peopleRepository.save(person);
		}
		return null;
	}
	public void deletePerson(Long id) {
		peopleRepository.deleteById(id);
	}
}
