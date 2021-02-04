package br.com.voisinonline.build;

import br.com.voisinonline.dto.PropertyDTO;
import br.com.voisinonline.dto.form.PropertyFormDTO;
import br.com.voisinonline.model.Property;

import java.time.LocalDateTime;

public interface BuildProperty {

    static Property buildProperty() {
        return Property.builder()
                .id("123")
                .name("Test")
                .description("voisin")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    static PropertyFormDTO buildPropertyFormDTO() {
        return PropertyFormDTO.builder()
                .name("Test voisin")
                .description("Description")
                .build();
    }

    static PropertyFormDTO buildPropertyFormDTOEmpty() {
        return PropertyFormDTO.builder().build();
    }

    static PropertyDTO buildPropertyDTO() {
        return PropertyDTO.builder()
                .id("123")
                .name("Test voisin")
                .description("Description")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
