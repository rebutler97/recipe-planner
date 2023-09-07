package com.rachelbutler.recipeplanner.model;

import java.util.UUID;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "ingredient")
public class Ingredient {
    
    @Id
    @GeneratedValue
    private UUID ingredientId;

    @NonNull
    private String name;
    
    @NonNull
    private String quantity;

    @ManyToOne
    // TODO: why does it still say recipe: null in response body?
    private Recipe recipe;

}
