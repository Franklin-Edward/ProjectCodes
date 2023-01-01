package com.brillio.problem.statement1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.problem.statement1.model.People;
import com.brillio.problem.statement1.service.PeopleService;


@RestController
@RequestMapping("/people")
public class PeopleController {
	
	@Autowired
	PeopleService peopleService;
	
	@PostMapping("/addperson")
    public People addTraining(@RequestBody People person){
		return peopleService.newPerson(person);
    }
	
	@GetMapping("/getpeople")
    public ResponseEntity<?> getAllPeople(){
        try{
    		List<People> getAllPeople = peopleService.getPeople();
    		if(getAllPeople.size()>=0) {
    			return ResponseEntity.ok().body(getAllPeople);
    		}
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data found ");        
    	}catch (Exception e){            
    		System.out.println(e.getStackTrace());           
    		return ResponseEntity.status(400).body("something went wrong");        
    	}

    }
	
	@GetMapping("/getperson/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable(value = "id") Long id){
    	try{
    		Optional<People> getPerson = peopleService.getPerson(id);
    		if(getPerson.isPresent()) {
    			return ResponseEntity.ok().body(getPerson);
    		}
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no Person found");        
    	}catch (Exception e){            
    		System.out.println(e.getStackTrace());           
    		return ResponseEntity.status(400).body("something went wrong");        
    	}    
    }
	
	@PutMapping("/editperson/{id}")
    public ResponseEntity<?> editTraining(@PathVariable(value="id") Long id, @RequestBody People person){
    	try{
    		People isUpdated = peopleService.updatePerson(id,person);
    		if(isUpdated!=null) {
    			return ResponseEntity.ok().body(isUpdated);
    		}
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Person found");        
    	}catch (Exception e){            
    		System.out.println(e.getStackTrace());           
    		return ResponseEntity.status(400).body("something went wrong");        
    	}
    }
    
    @DeleteMapping("/deleteperson/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id){
    	try {
    		Optional<People> getPerson = peopleService.getPerson(id);
    		if(getPerson.isPresent()) {
    			peopleService.deletePerson(id);
    			return ResponseEntity.status(200).body(getPerson);
    		}
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Person Found"); 
    		
    	}catch(Exception e) {
    		System.out.println(e.getStackTrace());           
    		return ResponseEntity.status(400).body("something went wrong"); 
    	}
    }

}
