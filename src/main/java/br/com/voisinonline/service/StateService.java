package br.com.voisinonline.service;

import br.com.voisinonline.dto.CityDTO;
import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.PropertySectorDTO;
import br.com.voisinonline.dto.StateDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.dto.form.StateFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Picket;
import br.com.voisinonline.model.Property;
import br.com.voisinonline.model.State;
import br.com.voisinonline.repository.StateRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StateService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final StateRepository repository;
    private final ModelMapper mapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public StateService(StateRepository repository, ModelMapper mapper, SequenceGeneratorService sequenceGeneratorService) {
        this.repository = repository;
        this.mapper = mapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public List<StateDTO> findAll() {
        Collection<State> states = repository.findAll();
        return states.stream().map(property -> mapper.map(property, StateDTO.class)).collect(Collectors.toList());
    }

    public StateDTO findById(Long id) {
        State state = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(state, StateDTO.class);
    }

    public List<CityDTO> findAllCitiesByStateId(Long id) {
        State state = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return state.getCities().stream()
                .map(city -> mapper.map(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    public StateDTO save(StateFormDTO stateFormDTO) {
        State state = mapper.map(stateFormDTO, State.class);
        state.setId(sequenceGeneratorService.generateSequence(Picket.SEQUENCE_NAME));
        repository.save(state);
        return mapper.map(state, StateDTO.class);
    }

    public StateDTO update(Long id, StateFormDTO stateFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        State state = mapper.map(stateFormDTO, State.class);
        state.setUpdatedAt(LocalDateTime.now());

        return mapper.map(repository.save(state), StateDTO.class);
    }

    public void delete(Long id) {
        State state = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(state);
    }
}