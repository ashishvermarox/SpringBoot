package shopping.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.model.Person;
import shopping.repository.PersonRepository;

@Service
public class PersonDAO {
	
	@Autowired
	PersonRepository pRepo;
	
	//search all
	public List<Person> getAllRecords(){
		return pRepo.findAll();
	}
	
	//search by ID
	public Person getByID(int id) {
		return pRepo.getOne(id);
	}
	
	//delete by ID
	public void deleteByID(int id) {
		pRepo.deleteById(id);
	}
	
	//insert to database
	public Person saveData(Person pr) {
		return pRepo.save(pr);
	}
	
	
}
