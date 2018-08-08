package fr.imie.recipemanager.dao;

import fr.imie.recipemanager.entity.Recipe;

import java.util.List;


public interface RecipeDao {
	
	List<Recipe> getSharedRecipes();
	//Recipe findRecipe()
	void addRecipe(Recipe r);
	void updateRecipe(Recipe r);
	void removeRecipe(Recipe r);
	/*void addIngredientInRecipe(Recipe r, List<Ingredient> ingredients);
	void deleteIngredientInRecipe(Recipe r, List<Ingredient> ingredients);*/

}
