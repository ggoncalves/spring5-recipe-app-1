package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecipeService implements CrudService<Recipe> {

  private final RecipeRepository recipeRepository;

  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Iterable<Recipe> findAll() {
    return recipeRepository.findAll();
  }
}
