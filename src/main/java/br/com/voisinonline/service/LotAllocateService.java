package br.com.voisinonline.service;

import br.com.voisinonline.dto.LotAllocateDTO;
import br.com.voisinonline.dto.form.LotAllocateFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Lot;
import br.com.voisinonline.model.LotAllocate;
import br.com.voisinonline.model.Picket;
import br.com.voisinonline.model.PropertySector;
import br.com.voisinonline.repository.LotAllocateRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotAllocateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotAllocateService.class);
    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final LotAllocateRepository repository;
    private final PropertySectorService propertySectorService;
    private final LotService lotService;
    private final PicketService picketService;
    private final ModelMapper mapper;

    public LotAllocateService(LotAllocateRepository repository, PropertySectorService propertySectorService,
                              LotService lotService, PicketService picketService, ModelMapper mapper) {
        this.repository = repository;
        this.propertySectorService = propertySectorService;
        this.lotService = lotService;
        this.picketService = picketService;
        this.mapper = mapper;
    }

    public List<LotAllocateDTO> findAll() {
        Collection<LotAllocate> lotAllocates = repository.findAll();
        return lotAllocates.stream()
                .map(lotAllocate -> mapper.map(lotAllocate, LotAllocateDTO.class))
                .collect(Collectors.toList());
    }

    public LotAllocateDTO findById(String id) {
        LotAllocate lotAllocate = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(lotAllocate, LotAllocateDTO.class);
    }

    public LotAllocateDTO save(LotAllocateFormDTO lotAllocateFormDTO) {
        Lot lot = lotService.findLotById(lotAllocateFormDTO.getLotId());
        PropertySector propertySector = propertySectorService.findPropertySectorById(lotAllocateFormDTO.getSectorId());
        Picket picket = picketService.findPicketById(lotAllocateFormDTO.getPicketId());
        LotAllocate lotAllocate = mapper.map(lotAllocateFormDTO, LotAllocate.class);
        lotAllocate.setId(null);
        lotAllocate.setLot(lot);
        lotAllocate.setSector(propertySector);
        lotAllocate.setPicket(picket);

        repository.save(lotAllocate);
        return mapper.map(lotAllocate, LotAllocateDTO.class);
    }

    public LotAllocateDTO update(String id, LotAllocateFormDTO lotAllocateFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));

        Lot lot = lotService.findLotById(lotAllocateFormDTO.getLotId());
        PropertySector propertySector = propertySectorService.findPropertySectorById(lotAllocateFormDTO.getSectorId());
        Picket picket = picketService.findPicketById(lotAllocateFormDTO.getPicketId());

        LotAllocate lotAllocate = mapper.map(lotAllocateFormDTO, LotAllocate.class);

        lotAllocate.setLot(lot);
        lotAllocate.setSector(propertySector);
        lotAllocate.setPicket(picket);
        picket.setUpdatedAt(LocalDateTime.now());

        return mapper.map(repository.save(lotAllocate), LotAllocateDTO.class);
    }

    public void delete(String id) {
        LotAllocate lotAllocate = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(lotAllocate);
    }
}