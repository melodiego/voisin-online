package br.com.voisinonline.service;

import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.PropertySectorDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.City;
import br.com.voisinonline.model.Property;
import br.com.voisinonline.model.State;
import br.com.voisinonline.repository.PropertyRepository;
import br.com.voisinonline.repository.PropertySectorRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final PropertyRepository repository;
    private final PropertySectorRepository propertySectorRepository;
    private final ModelMapper mapper;
    private final StateService stateService;

    public PropertyService(PropertyRepository repository, PropertySectorRepository propertySectorRepository,
                           ModelMapper mapper, StateService stateService) {
        this.repository = repository;
        this.propertySectorRepository = propertySectorRepository;
        this.mapper = mapper;
        this.stateService = stateService;
    }

    public List<PropertyDTO> findAll() {
        Collection<Property> properties = repository.findAll();
        return properties.stream().map(property -> mapper.map(property, PropertyDTO.class)).collect(Collectors.toList());
    }

    public PropertyDTO findById(String id) {
        Property property = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(property, PropertyDTO.class);
    }

    public Property findPropertyById(String id) {
        Property property = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(property, Property.class);
    }

    public List<PropertySectorDTO> findAllSectorsById(String id) {
        return propertySectorRepository.findAllByPropertyIdOrderByNameAsc(id).stream()
                .map(sector -> mapper.map(sector, PropertySectorDTO.class))
                .collect(Collectors.toList());
    }

    public PropertyDTO save(PropertyFormDTO propertyFormDTO) {
        City city = getCity(propertyFormDTO.getUfId(), propertyFormDTO.getCityCodeIbge());
        Property property = mapper.map(propertyFormDTO, Property.class);
        property.setId(null);
        property.setCity(city);
        repository.save(property);
        return mapper.map(property, PropertyDTO.class);
    }

    public PropertyDTO update(String id, PropertyFormDTO propertyFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));

        City city = getCity(propertyFormDTO.getUfId(), propertyFormDTO.getCityCodeIbge());
        Property property = mapper.map(propertyFormDTO, Property.class);
        property.setUpdatedAt(LocalDateTime.now());
        property.setCity(city);
        repository.save(property);

        return mapper.map(property, PropertyDTO.class);
    }

    public void delete(String id) {
        Property property = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(property);
    }

    private City getCity(Long ufId, String cityCodeIbge) {
        State state = stateService.findStateById(ufId);
        return state.getCities().stream()
                .filter(cityFilter -> cityCodeIbge.equals(cityFilter.getCodeIbge()))
                .findFirst()
                .get();
    }
}