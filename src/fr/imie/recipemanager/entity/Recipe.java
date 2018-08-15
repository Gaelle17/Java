package fr.imie.recipemanager.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	//private List<String> instructions;
	private Float difficultyLevel;
	private Long preparationTime;
	//private List<String> pictures;
	/*@ManyToMany(mappedBy="recipes")
	private List<Ingredient> ingredients;
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	*/
	public Recipe() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
*/
	public Float getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Float difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Long getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Long preparationTime) {
		this.preparationTime = preparationTime;
	}
/*
	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}*/

}
