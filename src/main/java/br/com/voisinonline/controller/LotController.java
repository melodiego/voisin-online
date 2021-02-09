package br.com.voisinonline.controller;

import br.com.voisinonline.dto.LotDTO;
import br.com.voisinonline.dto.form.LotFormDTO;
import br.com.voisinonline.service.LotService;
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
@RequestMapping("/lots")
public class LotController {

    private final LotService service;

    public LotController(LotService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<LotDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotDTO> getById(@PathVariable(value = "id") @Valid String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LotDTO> save(@RequestBody @Valid LotFormDTO lotFormDTO) {
        return new ResponseEntity<>(service.save(lotFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotDTO> update(@PathVariable("id") String id, @RequestBody @Valid LotFormDTO lotFormDTO) {
        return new ResponseEntity<>(service.update(id, lotFormDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") String id) {
        String codeEscape = HtmlUtils.htmlEscape(id);
        service.delete(codeEscape);
    }
}