package com.goku.heroes.controller;

import com.goku.heroes.exception.ResourceNotFoundException;
import com.goku.heroes.model.Hero;
import com.goku.heroes.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HeroController {

    @Autowired
    HeroRepository heroRepository;

    // Get All Heroes
    @GetMapping("/heroes")
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    // Create a new Hero
    @PostMapping("/heroes")
    public Hero createHero(@Valid @RequestBody Hero hero) {
        return heroRepository.save(hero);
    }

    // Get a Single Hero
    @GetMapping("/heroes/{id}")
    public Hero getHeroById(@PathVariable(value = "id") Long heroId) {
        return heroRepository.findById(heroId).orElseThrow(() -> new ResourceNotFoundException("Hero", "id", heroId));
    }

    // Delete a Hero
    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<?> deleteHero(@PathVariable(value = "id") Long heroId) {
        Hero hero = heroRepository.findById(heroId).orElseThrow(() -> new ResourceNotFoundException("Hero", "id", heroId));
        heroRepository.delete(hero);
        return ResponseEntity.ok().build();
    }

    // Update a Hero
    @PutMapping("/heroes/{id}")
    public Hero updateHero(@PathVariable(value = "id") Long heroId, @Valid @RequestBody Hero heroDetails) {
        Hero hero = heroRepository.findById(heroId).orElseThrow(() -> new ResourceNotFoundException("Hero", "id", heroId));
        hero.setName(heroDetails.getName());
        Hero updatedHero = heroRepository.save(hero);
        return updatedHero;
    }

    // Get all Heroes who's name is like the param
    @GetMapping("/heroes/searchContaining")
    public List<Hero> searchHeroesContaining(@RequestParam("name") String name) {
        return heroRepository.findByNameContaining(name);
    }
}
