package com.rachelbutler.recipeplanner.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rachelbutler.recipeplanner.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> { }
