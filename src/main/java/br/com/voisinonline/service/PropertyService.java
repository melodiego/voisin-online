package br.com.voisinonline.service;

import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Property;
import br.com.voisinonline.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final PropertyRepository repository;
    private final ModelMapper mapper;

    public PropertyService(PropertyRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PropertyDTO> findAll() {
        Collection<Property> pautas = repository.findAll();
        return pautas.stream().map(pauta -> mapper.map(pauta, PropertyDTO.class)).collect(Collectors.toList());
    }

    public PropertyDTO findById(String id) {
        Property property = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(property, PropertyDTO.class);
    }

    public PropertyDTO save(PropertyFormDTO propertyFormDTO) {
        Property property = repository.save(mapper.map(propertyFormDTO, Property.class));
        return mapper.map(property, PropertyDTO.class);
    }

    public PropertyDTO update(String id, PropertyFormDTO propertyFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        Property property = mapper.map(propertyFormDTO, Property.class);
        property.setUpdatedAt(LocalDateTime.now());

        return mapper.map(repository.save(property), PropertyDTO.class);
    }

    public void delete(String id) {
        Property property = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(property);
    }
}