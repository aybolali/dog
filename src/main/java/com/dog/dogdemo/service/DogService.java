package com.dog.dogdemo.service;

import com.dog.dogdemo.domain.Dog;

import java.util.List;

public interface DogService {

    Dog addDog(Dog dog);

    List<Dog> getDogs();

    Dog updateDog(Dog dog, Long id);

    Dog getDogById(Long id);

    void deleteDog(Long id);

    List<Dog> searchForAll(String word);
}
