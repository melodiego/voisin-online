package br.com.voisinonline.service;

import br.com.voisinonline.build.BuildProperty;
import br.com.voisinonline.build.BuildState;
import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.Property;
import br.com.voisinonline.model.State;
import br.com.voisinonline.repository.PropertyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceTest {
    private static final String id = "1231";

    @Mock
    private PropertyRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private StateService stateService;

    @Mock
    private PropertyService service;

    @Test
    public void shouldSavePropertyDTOValidTest() {
        Property PropertyIn = BuildProperty.buildProperty();
        Property PropertyOut = BuildProperty.buildProperty();
        PropertyFormDTO PropertyFormDTO = BuildProperty.buildPropertyFormDTO();
        PropertyDTO PropertyDTO = BuildProperty.buildPropertyDTO();
        when(repository.save(PropertyIn)).thenReturn(PropertyOut);
        when(mapper.map(any(), eq(Property.class))).thenReturn(PropertyIn);
        when(mapper.map(any(), eq(PropertyDTO.class))).thenReturn(PropertyDTO);

        PropertyDTO PropertyDTOSaved = service.save(PropertyFormDTO);
        assertEquals(PropertyDTOSaved, PropertyDTO);

        verify(repository).save(eq(PropertyIn));
    }

    @Test
    public void shouldUpdatePropertyDTOValidTest() {
        Property propertyIn = BuildProperty.buildProperty();
        Property propertyOut = BuildProperty.buildProperty();
        PropertyFormDTO propertyFormDTO = BuildProperty.buildPropertyFormDTO();
        State state = BuildState.buildState();
        PropertyDTO propertyDTO = BuildProperty.buildPropertyDTO();

        when(repository.findById(id)).thenReturn(Optional.of(propertyIn));
        when(repository.save(propertyIn)).thenReturn(propertyOut);
        when(mapper.map(any(), eq(Property.class))).thenReturn(propertyIn);
        when(mapper.map(any(), eq(PropertyDTO.class))).thenReturn(propertyDTO);
        when(stateService.findStateById(1L)).thenReturn(state);
        PropertyDTO propertyDTOSaved = service.update(id, propertyFormDTO);
        assertEquals(propertyDTOSaved, propertyDTO);
        verify(repository).save(eq(propertyIn));
    }

    @Test(expected = RecordNotFoundException.class)
    public void shouldUpdatePropertyInvalidThrowRecordNotFoundExceptionTest() {
        PropertyFormDTO propertyFormDTO = BuildProperty.buildPropertyFormDTO();
        when(repository.findById(id)).thenReturn(Optional.empty());
        service.update(id, propertyFormDTO);
    }

    @Test
    public void shouldFindByIdValidTest() {
        Optional<Property> property = Optional.of(BuildProperty.buildProperty());
        PropertyDTO propertyDTO = BuildProperty.buildPropertyDTO();

        when(repository.findById(id)).thenReturn(property);
        when(mapper.map(any(), eq(PropertyDTO.class))).thenReturn(propertyDTO);

        PropertyDTO propertyDTOSaved = service.findById(id);
        assertNotNull(propertyDTOSaved);
        assertEquals(propertyDTO, propertyDTOSaved);
        assertNotNull(propertyDTO.getId());
        verify(repository).findById(id);
    }

    @Test(expected = RecordNotFoundException.class)
    public void shouldFindByIdInvalidTest() {
        Optional<Property> entity = Optional.empty();
        when(repository.findById(id)).thenReturn(entity);
        service.findById(id);
        verify(repository).findById(id);
    }

    @Test(expected = RecordNotFoundException.class)
    public void shouldDeleteInvalidTest() {
        Optional<Property> entity = Optional.empty();
        when(repository.findById(id)).thenReturn(entity);
        service.delete(id);
    }

    @Test
    public void shouldFindAllPropertyTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(BuildProperty.buildProperty()));
        List<PropertyDTO> propertiesDTO = service.findAll();
        assertEquals(propertiesDTO.size(), 1);
        verify(repository).findAll();
    }
}