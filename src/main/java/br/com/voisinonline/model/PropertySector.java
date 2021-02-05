package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "property_sector")
public class PropertySector implements Serializable {
    private static final long serialVersionUID = 6395963032200324766L;

    @Id
    private String id;
    @Indexed
    @NotBlank
    private String name;
    private String description;
    private String colorHex;

    @DBRef(lazy = false)
    @NotNull
    private Property property;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}