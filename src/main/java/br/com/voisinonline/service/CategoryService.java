package br.com.voisinonline.service;

import br.com.voisinonline.dto.CategoryDTO;
import br.com.voisinonline.dto.form.CategoryFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Category;
import br.com.voisinonline.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final CategoryRepository repository;
    private final ModelMapper mapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public CategoryService(CategoryRepository repository, ModelMapper mapper, SequenceGeneratorService sequenceGeneratorService) {
        this.repository = repository;
        this.mapper = mapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public List<CategoryDTO> findAll() {
        Collection<Category> categories = repository.findAll();
        return categories.stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        return mapper.map(category, CategoryDTO.class);
    }

    public Category findCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
    }

    public CategoryDTO save(CategoryFormDTO categoryFormDTO) {
        Category category = mapper.map(categoryFormDTO, Category.class);
        category.setId(sequenceGeneratorService.generateSequence(Category.SEQUENCE_NAME));
        repository.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO update(Long id, CategoryFormDTO categoryFormDTO) {
        repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));

        Category category = mapper.map(categoryFormDTO, Category.class);
        category.setUpdatedAt(LocalDateTime.now());
        repository.save(category);

        return mapper.map(category, CategoryDTO.class);
    }

    public void delete(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        repository.delete(category);
    }
}