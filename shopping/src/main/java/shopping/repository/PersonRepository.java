package shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
