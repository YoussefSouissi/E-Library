package com.example.ELibrary.Controller;

import com.example.ELibrary.Repositories.CategoryRepository;
import com.example.ELibrary.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/GetCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent()) {
                return new ResponseEntity<>(category, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCategories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryRepository.save(category);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        try {
            updatedCategory.setId(id);
            Category savedCategory = categoryRepository.save(updatedCategory);
            return new ResponseEntity<>(savedCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

