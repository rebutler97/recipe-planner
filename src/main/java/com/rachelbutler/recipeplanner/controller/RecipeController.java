package com.rachelbutler.recipeplanner.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.rachelbutler.recipeplanner.model.Recipe;
import com.rachelbutler.recipeplanner.repository.RecipeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {
    
    private RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes")
    public Collection<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable UUID id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/recipes")
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) throws URISyntaxException {
        Recipe result = recipeRepository.save(recipe);
        return ResponseEntity.created(new URI("/api/v1/recipes/" + result.getRecipeId())).body(result);
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe result = recipeRepository.save(recipe);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable UUID id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
