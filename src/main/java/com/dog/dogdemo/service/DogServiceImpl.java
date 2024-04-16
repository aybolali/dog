package com.dog.dogdemo.service;

import com.dog.dogdemo.domain.Dog;
import com.dog.dogdemo.exception.DogCheckException;
import com.dog.dogdemo.exception.DogNotFoundException;
import com.dog.dogdemo.repository.DogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog addDog(Dog dog) {
        String name = dog.getNameOfDog();
        if(dogAlreadyExists(name)){
            throw new DogCheckException("name of dog which is " + name + " already exists.");
        }
        return dogRepository.save(dog);
    }

    private boolean dogAlreadyExists(String name) {
        //mark that each dog has unique names
        return dogRepository.findByNameOfDog(name).isPresent();
    }

    @Override
    public List<Dog> getDogs() {
        return dogRepository.findAll();
    }

    @Override
    public Dog updateDog(Dog dog, Long id) {
        return dogRepository.findById(id).map(dog1 -> {
            dog1.setNameOfDog(dog.getNameOfDog());
            dog1.setNameOfOwner(dog.getNameOfOwner());
            dog1.setAge(dog.getAge());
            dog1.setBreed(dog.getBreed());
            dog1.setColor(dog.getColor());
            if(dogAlreadyExists(dog1.getNameOfDog()))
                throw new DogCheckException("name of dog which is " + dog1.getNameOfDog() + " already exists.");
            return dogRepository.save(dog1);
        }).orElseThrow(() -> new DogNotFoundException("this dog not found"));
    }

    @Override
    @Transactional
    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElseThrow(() -> new DogNotFoundException("with id : " + id  + " dog has not found"));
    }

    @Override
    public void deleteDog(Long id) {
        if(!dogRepository.existsById(id)){
            throw new DogNotFoundException("dog not found for id: " + id);
        }
        dogRepository.deleteById(id);
    }

    @Override
    public List<Dog> searchForAll(String word) {
        if(word != null) {
            return dogRepository.search(word);
        }
        return dogRepository.findAll();
    }
}
