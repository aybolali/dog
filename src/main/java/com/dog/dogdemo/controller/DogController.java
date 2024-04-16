package com.dog.dogdemo.controller;

import com.dog.dogdemo.domain.Dog;
import com.dog.dogdemo.exception.DogNotFoundException;
import com.dog.dogdemo.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping(path = {"/home", "/search"})
    public String homePage(Model model, @ModelAttribute("word") @Param("word") String word){
        List<Dog> dogList = dogService.searchForAll(word);;
        model.addAttribute("dogList", dogList);
        return "home";
    }
    @GetMapping("/newDog")
    public String newDogAddFormPage(Model model){
        model.addAttribute("newDog", new Dog());

        return "newDogAddForm";
    }

    @PostMapping("/addDog")
    public String addDog(@ModelAttribute("dogToAdd") Dog dog){
        dogService.addDog(dog);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String showPageForUpdateDog(@PathVariable Long id, Model model){
        model.addAttribute("dog", dogService.getDogById(id));

        return "updateDogForm";
    }

    @PostMapping ("/updateDog")
    public String updateDog(@ModelAttribute("updateDog") Dog dog){

        dogService.updateDog(dog, dog.getId());
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        dogService.deleteDog(id);
        return "redirect:/home";
    }

    @GetMapping("/dog/{id}")
    public Dog getDogById(@PathVariable Long id){
        return dogService.getDogById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DogNotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("error404");

        return modelAndView;
    }
    @RequestMapping("/updateDog")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String error500(@ModelAttribute("e") Exception e){

        return "error500";
    }
}
