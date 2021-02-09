package br.com.voisinonline.controller;

import br.com.voisinonline.dto.CategoryDTO;
import br.com.voisinonline.dto.form.CategoryFormDTO;
import br.com.voisinonline.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable(value = "id") @Valid Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody @Valid CategoryFormDTO categoryFormDTO) {
        return new ResponseEntity<>(service.save(categoryFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryFormDTO categoryFormDTO) {
        return new ResponseEntity<>(service.update(id, categoryFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}