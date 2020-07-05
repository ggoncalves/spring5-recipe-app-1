package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

  private final RecipeRepository recipeRepository;
  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
    this.recipeRepository = recipeRepository;
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Recipe recipe1 = new Recipe();

    recipe1.setName("Spicy Grilled Chicken Tacos");

    // Description
    recipe1.setDescription("Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
        "\n" +
        "\n" +
        "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
        "\n" +
        "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");

    // prepTime;
    recipe1.setPrepTime(20);
    // cookTime;
    recipe1.setCookTime(15);

    //  servings;
    recipe1.setServings(6);
    // source;
    recipe1.setSource("Simply Recipes Site");
    // url;
    recipe1.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
    // directions;
    recipe1.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
        "\n" +
        "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
        "\n" +
        "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
        "\n" +
        "Spicy Grilled Chicken Tacos\n" +
        "\n" +
        "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
        "\n" +
        "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
        "\n" +
        "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
        "\n" +
        "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

    // ingredients;

    Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
    Ingredient ancho = new Ingredient();
    ancho.setAmount(new BigDecimal("2"));
    ancho.setUom(tablespoon.get());
    ancho.setDescription("ancho chili powder");
    ancho.setRecipe(recipe1);

    recipe1.getIngredients().add(ancho);

    Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
    Ingredient sugar = new Ingredient();
    sugar.setDescription("sugar");
    sugar.setAmount(BigDecimal.ONE);
    sugar.setUom(teaspoon.get());
    sugar.setRecipe(recipe1);

    recipe1.getIngredients().add(sugar);

    // notes;
    Notes notes = new Notes();
    notes.setRecipeNotes("This is the Notes");
    notes.setRecipe(recipe1);
    recipe1.setNotes(notes);

    // categories;
    Optional<Category> mexican = categoryRepository.findByDescription("Mexican");

    recipe1.getCategories().add(mexican.get());

    // image;

    // difficulty;
    recipe1.setDifficulty(Difficulty.MODERATE);


    Recipe savedRecipe = recipeRepository.save(recipe1);


    System.out.println("Saved with id: " + savedRecipe.getId());
    System.out.println("Note with id: " + savedRecipe.getNotes().getId());
    System.out.println("Note with recipe id: " + savedRecipe.getNotes().getRecipe().getId());

  }
}
