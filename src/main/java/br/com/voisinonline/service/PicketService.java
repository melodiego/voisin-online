package br.com.voisinonline.service;

import br.com.voisinonline.dto.PicketDTO;
import br.com.voisinonline.dto.form.PicketFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Picket;
import br.com.voisinonline.model.PropertySector;
import br.com.voisinonline.repository.PicketRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PicketService.class);
    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final PicketRepository repository;
    private final PropertySectorService propertySectorService;
    private final ModelMapper mapper;

    public PicketService(PicketRepository repository, PropertySectorService propertySectorService, ModelMapper mapper) {
        this.repository = repository;
        this.propertySectorService = propertySectorService;
        this.mapper = mapper;
    }

    public List<PicketDTO> findAll() {
        Collection<Picket> picket = repository.findAll();
        return picket.stream()
                .map(picketMap -> mapper.map(picketMap, PicketDTO.class))
                .collect(Collectors.toList());
    }

    public PicketDTO findById(String id) {
        Picket picket = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(picket, PicketDTO.class);
    }

    public Picket findPicketById(String id) {
        return repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
    }

    public PicketDTO save(PicketFormDTO picketFormDTO) {
        PropertySector propertySector = propertySectorService.findPropertySectorById(picketFormDTO.getPropertySectorId());
        Picket picket = mapper.map(picketFormDTO, Picket.class);
        picket.setId(null);
        picket.setSector(propertySector);
        repository.save(picket);
        return mapper.map(picket, PicketDTO.class);
    }

    public PicketDTO update(String id, PicketFormDTO picketFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));

        PropertySector propertySector = propertySectorService.findPropertySectorById(picketFormDTO.getPropertySectorId());
        Picket picket = mapper.map(picketFormDTO, Picket.class);

        picket.setSector(propertySector);
        picket.setUpdatedAt(LocalDateTime.now());
        return mapper.map(repository.save(picket), PicketDTO.class);
    }

    public void delete(String id) {
        Picket picket = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(picket);
    }
}