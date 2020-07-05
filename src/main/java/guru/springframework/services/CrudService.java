package guru.springframework.services;

import java.util.Set;

public interface CrudService<T> {
  Iterable<T> findAll();
}