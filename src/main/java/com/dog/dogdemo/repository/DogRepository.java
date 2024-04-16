package com.dog.dogdemo.repository;

import com.dog.dogdemo.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
   Optional<Dog> findByNameOfDog(String name);

   @Query(value = "SELECT * FROM dog s WHERE s.name_of_dog LIKE %:word%",
           nativeQuery = true)
   List<Dog> search(@Param("word") String word);
}
