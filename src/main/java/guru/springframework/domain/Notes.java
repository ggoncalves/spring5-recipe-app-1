package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Notes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Recipe recipe;

  @Lob
  private String recipeNotes;

  @ToString.Include
  private Long getRecipeId() {
    return (this.recipe == null) ? null : this.recipe.getId();
  }

}
