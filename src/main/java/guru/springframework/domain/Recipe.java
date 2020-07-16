package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Lob
  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  @Lob
  private String directions;
  // TODO add
  // private Difficulty difficulty;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients;

  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  @ManyToMany
  @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories;

  @Lob
  private Byte[] image;

  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;

  public void setNotes(Notes notes) {
    this.notes = notes;
    this.notes.setRecipe(this);
  }

  public void addIngredient(Ingredient ingredient) {
    ingredient.setRecipe(this);
    this.getIngredients().add(ingredient);
  }

  public Set<Ingredient> getIngredients() {
    if (ingredients == null) {
      ingredients = new HashSet<>();
    }
    return ingredients;
  }

  public Set<Category> getCategories() {
    if (categories == null) {
      categories = new HashSet<>();
    }
    return categories;
  }
}
