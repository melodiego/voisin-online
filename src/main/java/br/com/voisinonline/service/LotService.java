package br.com.voisinonline.service;

import br.com.voisinonline.dto.LotDTO;
import br.com.voisinonline.dto.form.LotFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Lot;
import br.com.voisinonline.model.PropertySector;
import br.com.voisinonline.repository.LotRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotService.class);
    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final LotRepository repository;
    private final PropertySectorService propertySectorService;
    private final ModelMapper mapper;

    public LotService(LotRepository repository, PropertySectorService propertySectorService, ModelMapper mapper) {
        this.repository = repository;
        this.propertySectorService = propertySectorService;
        this.mapper = mapper;
    }

    public List<LotDTO> findAll() {
        Collection<Lot> lots = repository.findAll();
        return lots.stream()
                .map(lot -> mapper.map(lot, LotDTO.class))
                .collect(Collectors.toList());
    }

    public LotDTO findById(String id) {
        Lot lot = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(lot, LotDTO.class);
    }

    public LotDTO save(LotFormDTO LotFormDTO) {
        PropertySector propertySector = propertySectorService.findPropertySectorById(LotFormDTO.getPropertySectorId());
        Lot lot = mapper.map(LotFormDTO, Lot.class);
        lot.setId(null);
        lot.setSector(propertySector);
        repository.save(lot);
        return mapper.map(lot, LotDTO.class);
    }

    public LotDTO update(String id, LotFormDTO LotFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));

        PropertySector propertySector = propertySectorService.findPropertySectorById(LotFormDTO.getPropertySectorId());
        Lot lot = mapper.map(LotFormDTO, Lot.class);

        lot.setSector(propertySector);
        lot.setUpdatedAt(LocalDateTime.now());
        return mapper.map(repository.save(lot), LotDTO.class);
    }

    public void delete(String id) {
        Lot lot = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(lot);
    }
}