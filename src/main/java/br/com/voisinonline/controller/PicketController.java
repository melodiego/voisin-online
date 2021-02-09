package br.com.voisinonline.controller;

import br.com.voisinonline.dto.PicketDTO;
import br.com.voisinonline.dto.form.PicketFormDTO;
import br.com.voisinonline.service.PicketService;
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
@RequestMapping("/pickets")
public class PicketController {

    private final PicketService service;

    public PicketController(PicketService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<PicketDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PicketDTO> getById(@PathVariable(value = "id") @Valid String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PicketDTO> save(@RequestBody @Valid PicketFormDTO picketFormDTO) {
        return new ResponseEntity<>(service.save(picketFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PicketDTO> update(@PathVariable("id") String id, @RequestBody @Valid PicketFormDTO picketFormDTO) {
        return new ResponseEntity<>(service.update(id, picketFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        String codeEscape = HtmlUtils.htmlEscape(id);
        service.delete(codeEscape);
    }
}