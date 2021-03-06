package br.com.voisinonline.service;

import br.com.voisinonline.dto.LotDTO;
import br.com.voisinonline.dto.PicketDTO;
import br.com.voisinonline.dto.PropertySectorDTO;
import br.com.voisinonline.dto.form.PropertySectorFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Property;
import br.com.voisinonline.model.PropertySector;
import br.com.voisinonline.repository.LotRepository;
import br.com.voisinonline.repository.PicketRepository;
import br.com.voisinonline.repository.PropertySectorRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertySectorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertySectorService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final PropertySectorRepository repository;
    private final PicketRepository picketRepository;
    private final PropertyService propertyService;
    private final LotRepository lotRepository;
    private final ModelMapper mapper;

    public PropertySectorService(PropertySectorRepository repository, PropertyService propertyService, ModelMapper mapper,
                                 PicketRepository picketRepository, LotRepository lotRepository) {
        this.repository = repository;
        this.propertyService = propertyService;
        this.mapper = mapper;
        this.picketRepository = picketRepository;
        this.lotRepository = lotRepository;
    }

    public List<PropertySectorDTO> findAll() {
        Collection<PropertySector> propertySectors = repository.findAll();
        return propertySectors.stream()
                .map(propertySector -> mapper.map(propertySector, PropertySectorDTO.class))
                .collect(Collectors.toList());
    }

    public PropertySectorDTO findById(String id) {
        PropertySector propertySector = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(propertySector, PropertySectorDTO.class);
    }

    public PropertySector findPropertySectorById(String id) {
        PropertySector propertySector = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return propertySector;
    }

    public List<PicketDTO> findAllPicketsById(String id) {
        return picketRepository.findAllBySectorIdOrderByNameAsc(id).stream()
                .map(picket -> mapper.map(picket, PicketDTO.class))
                .collect(Collectors.toList());
    }

    public List<LotDTO> findAllLotsById(String id) {
        return lotRepository.findAllBySectorIdOrderByNameAsc(id).stream()
                .map(lot -> mapper.map(lot, LotDTO.class))
                .collect(Collectors.toList());
    }

    public PropertySectorDTO save(PropertySectorFormDTO propertySectorFormDTO) {
        Property property = propertyService.findPropertyById(propertySectorFormDTO.getPropertyId());
        PropertySector propertySector = mapper.map(propertySectorFormDTO, PropertySector.class);
        propertySector.setId(null);
        propertySector.setProperty(property);
        repository.save(propertySector);
        return mapper.map(propertySector, PropertySectorDTO.class);
    }

    public PropertySectorDTO update(String id, PropertySectorFormDTO propertySectorFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        Property property = propertyService.findPropertyById(propertySectorFormDTO.getPropertyId());

        PropertySector propertySector = mapper.map(propertySectorFormDTO, PropertySector.class);
        propertySector.setProperty(property);
        propertySector.setUpdatedAt(LocalDateTime.now());
        return mapper.map(repository.save(propertySector), PropertySectorDTO.class);
    }

    public void delete(String id) {
        PropertySector propertySector = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(propertySector);
    }
}