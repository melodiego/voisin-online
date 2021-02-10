package br.com.voisinonline.controller;


import br.com.voisinonline.dto.LotAllocateDTO;
import br.com.voisinonline.dto.form.LotAllocateFormDTO;
import br.com.voisinonline.service.LotAllocateService;
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
@RequestMapping("/lot-allocates")
public class LotAllocateController {

    private final LotAllocateService service;

    public LotAllocateController(LotAllocateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<LotAllocateDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotAllocateDTO> getById(@PathVariable(value = "id") @Valid String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LotAllocateDTO> save(@RequestBody @Valid LotAllocateFormDTO lotAllocateFormDTO) {
        return new ResponseEntity<>(service.save(lotAllocateFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotAllocateDTO> update(@PathVariable("id") String id, @RequestBody @Valid LotAllocateFormDTO lotAllocateFormDTO) {
        return new ResponseEntity<>(service.update(id, lotAllocateFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        String codeEscape = HtmlUtils.htmlEscape(id);
        service.delete(codeEscape);
    }
}