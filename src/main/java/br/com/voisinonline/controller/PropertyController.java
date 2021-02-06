package br.com.voisinonline.controller;

import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.PropertySectorDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<PropertyDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getById(@PathVariable(value = "id") @Valid String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/sectors")
    public ResponseEntity<List<PropertySectorDTO>> getByAllSectorsBy(@PathVariable(value = "id") @Valid String id) {
        return new ResponseEntity<>(service.findAllSectorsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> save(@RequestBody @Valid PropertyFormDTO propertyFormDTO) {
        return new ResponseEntity<>(service.save(propertyFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> update(@PathVariable("id") String id, @RequestBody @Valid PropertyFormDTO propertyFormDTO) {
        return new ResponseEntity<>(service.update(id, propertyFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        String codeEscape = HtmlUtils.htmlEscape(id);
        service.delete(codeEscape);
    }
}