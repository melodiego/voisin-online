package br.com.voisinonline.controller;

import br.com.voisinonline.build.BuildProperty;
import br.com.voisinonline.build.BuildState;
import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.exception.BadRequestException;
import br.com.voisinonline.exception.RecordNotFoundException;
import br.com.voisinonline.model.State;
import br.com.voisinonline.repository.StateRepository;
import br.com.voisinonline.service.PropertyService;
import br.com.voisinonline.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.voisinonline.util.Constants.*;
import static br.com.voisinonline.util.Constants.PropertyConstants.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StateService stateService;

    @MockBean
    private PropertyService service;

    private final ObjectMapper mapper = new ObjectMapper();
    private static final String endpointAPI = "/properties";

    @Test
    public void shouldCreatePropertyWithAValidRequestTest() throws Exception {
        PropertyDTO propertyDTO = BuildProperty.buildPropertyDTO();
        PropertyFormDTO propertyFormDTO = BuildProperty.buildPropertyFormDTO();
        State state = BuildState.buildState();
        when(stateService.findStateById(1L)).thenReturn(state);
        when(service.save(any())).thenReturn(propertyDTO);

        mockMvc.perform(
                post(endpointAPI).content(mapper.writeValueAsString(propertyFormDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(propertyDTO.getId()))
                .andExpect(jsonPath("$.name").value(propertyFormDTO.getName()))
                .andExpect(jsonPath("$.totalPastureArea").value(propertyFormDTO.getTotalPastureArea()))
                .andExpect(jsonPath("$.description").value(propertyFormDTO.getDescription()))
                .andExpect(jsonPath("$.createdAt").exists());

        verify(service).save(eq(propertyFormDTO));
    }

    @Test
    public void shouldCreatePropertyWithAnInvalidRequestTest() throws Exception {
        PropertyFormDTO propertyFormDTO = BuildProperty.buildPropertyFormDTOEmpty();
        mockMvc.perform(
                post(endpointAPI).content(mapper.writeValueAsString(propertyFormDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdatePropertyWithAValidRequestTest() throws Exception {
        PropertyDTO propertyDTO = BuildProperty.buildPropertyDTO();
        PropertyFormDTO propertyFormDTO = BuildProperty.buildPropertyFormDTO();
        State state = BuildState.buildState();
        when(stateService.findStateById(1L)).thenReturn(state);
        when(service.update(anyString(), eq(propertyFormDTO))).thenReturn(propertyDTO);
        mockMvc.perform(put((String.format("%s/%s", endpointAPI, "123")))
                .content(mapper.writeValueAsString(propertyDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(propertyDTO.getId()))
                .andExpect(jsonPath("$.name").value(propertyFormDTO.getName()))
                .andExpect(jsonPath("$.totalPastureArea").value(propertyFormDTO.getTotalPastureArea()))
                .andExpect(jsonPath("$.description").value(propertyFormDTO.getDescription()))
                .andExpect(jsonPath("$.createdAt").exists());

        verify(service).update(anyString(), eq(propertyFormDTO));
    }

    @Test
    public void shouldUpdatePropertyWithAnInvalidRequestTest() throws Exception {
        PropertyFormDTO propertyFormDTO = BuildProperty.buildPropertyFormDTOEmpty();
        mockMvc.perform(put((String.format("%s/%s", endpointAPI, "123")))
                .content(mapper.writeValueAsString(propertyFormDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldGetPropertyWithAValidIdTest() throws Exception {
        PropertyDTO propertyDTO = BuildProperty.buildPropertyDTO();
        when(service.findById(anyString())).thenReturn(propertyDTO);
        String id = "123";

        mockMvc.perform(get((String.format("%s/%s", endpointAPI, id))))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.id").value(propertyDTO.getId()))
                .andExpect(jsonPath("$.name").value(propertyDTO.getName()))
                .andExpect(jsonPath("$.description").value(propertyDTO.getDescription()))
                .andExpect(jsonPath("$.createdAt").exists());

        verify(service).findById(id);
    }

    @Test
    public void shouldGetPropertyWithAnInvalidIdTest() throws Exception {
        when(service.findById(eq(" "))).thenThrow(new BadRequestException(THE_ID_CANNOT_BE_EMPTY));
        mockMvc.perform(get((String.format("%s/%s", endpointAPI, " "))))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.message").value(KEY_BAD_REQUEST))
                .andExpect(jsonPath("$.details[0]").value(THE_ID_CANNOT_BE_EMPTY));
        verify(service).findById(eq(" "));
    }

    @Test
    public void shouldDeletePropertyWithAValidIdTest() throws Exception {
        mockMvc.perform(delete((String.format("%s/%s", endpointAPI, "123"))))
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldDeletePropertyWithAnInvalidIdTest() throws Exception {
        String id = "123";
        doThrow(new RecordNotFoundException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id))
                .when(service).delete(eq(id));
        mockMvc.perform(delete((String.format("%s/%s", endpointAPI, id))))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.message").value(KEY_NOT_FOUND))
                .andExpect(jsonPath("$.details[0]").value(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id));
        verify(service).delete(eq(id));
    }

    @Test
    public void shouldGetAllProperties() throws Exception {
        PropertyDTO propertyDTO = BuildProperty.buildPropertyDTO();

        when(service.findAll()).thenReturn(Collections.singletonList(propertyDTO));
        mockMvc.perform(
                get(endpointAPI).content(mapper.writeValueAsString(propertyDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service).findAll();
    }
}