package shopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.dao.PersonDAO;
import shopping.model.Person;

@RestController
@RequestMapping("/shopping")
public class PersonController {
	
	@Autowired
	PersonDAO pdao;
	
	//search all
	@GetMapping("/alldata")
	public List<Person> getData(){
		return pdao.getAllRecords();
	}
	
	//search by id
	@GetMapping("/getperson/{id}")
	public ResponseEntity<Person> getById(@PathVariable(value = "id") Integer pid){
		Person np = pdao.getByID(pid);
		if (np == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().body(np);
	}
	
	//delete by id
	@DeleteMapping("/deleteperson/{id}")
	public ResponseEntity<Person> deleteByID(@PathVariable(value = "id") Integer pid){
		Person np = pdao.getByID(pid);
		if (np == null)	
			return ResponseEntity.notFound().build();
		else {
			pdao.deleteByID(pid);
			return ResponseEntity.ok().build();
		}
	}
	
	//insert by id
	@PostMapping("/saveperson")
	public Person saveNewPerson(@Valid @RequestBody Person np){
		return pdao.saveData(np);
	}
	
	@PutMapping("/updateperson/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Integer pid, @Valid @RequestBody Person pDetails){
		
		Person tmpPr = pdao.getByID(pid);
		
		if(tmpPr == null)
			return ResponseEntity.notFound().build();
		
		else {
			
			tmpPr.setFirstName(pDetails.getFirstName());
			tmpPr.setLastName(pDetails.getLastName());
			tmpPr.setAge(pDetails.getAge());
			
			Person updt = pdao.saveData(tmpPr);
			return ResponseEntity.ok().body(updt);
			
		}
	}
	
}
