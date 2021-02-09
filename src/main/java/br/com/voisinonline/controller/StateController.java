package br.com.voisinonline.controller;

import br.com.voisinonline.dto.*;
import br.com.voisinonline.dto.form.PicketFormDTO;
import br.com.voisinonline.dto.form.StateFormDTO;
import br.com.voisinonline.service.PropertyService;
import br.com.voisinonline.service.StateService;
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
@RequestMapping("/states")
public class StateController {

    private final StateService service;

    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<StateDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> getById(@PathVariable(value = "id") @Valid Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/cities")
    public ResponseEntity<List<CityDTO>> getByAllCitiesBy(@PathVariable(value = "id") @Valid Long id) {
        return new ResponseEntity<>(service.findAllCitiesByStateId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StateDTO> save(@RequestBody @Valid StateFormDTO stateFormDTO) {
        return new ResponseEntity<>(service.save(stateFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> update(@PathVariable("id") Long id, @RequestBody @Valid StateFormDTO stateFormDTO) {
        return new ResponseEntity<>(service.update(id, stateFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}