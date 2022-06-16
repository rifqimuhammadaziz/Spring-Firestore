package rifqimuhammadaziz.springfirebase.controller;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rifqimuhammadaziz.springfirebase.entity.Category;
import rifqimuhammadaziz.springfirebase.service.CategoryService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategories() throws ExecutionException, InterruptedException {
        return categoryService.findAllCategory();
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }
}
