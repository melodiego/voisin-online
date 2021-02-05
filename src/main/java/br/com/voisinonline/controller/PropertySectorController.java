package br.com.voisinonline.controller;

import br.com.voisinonline.dto.PropertySectorDTO;
import br.com.voisinonline.dto.form.PropertySectorFormDTO;
import br.com.voisinonline.service.PropertySectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.util.Collection;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/property-sectors")
public class PropertySectorController {

    private final PropertySectorService service;

    public PropertySectorController(PropertySectorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<PropertySectorDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertySectorDTO> getById(@PathVariable(value = "id") @Valid String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PropertySectorDTO> save(@RequestBody @Valid PropertySectorFormDTO propertySectorFormDTO) {
        return new ResponseEntity<>(service.save(propertySectorFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertySectorDTO> update(@PathVariable("id") String id, @RequestBody @Valid PropertySectorFormDTO propertySectorFormDTO) {
        return new ResponseEntity<>(service.update(id, propertySectorFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        String codeEscape = HtmlUtils.htmlEscape(id);
        service.delete(codeEscape);
    }
}