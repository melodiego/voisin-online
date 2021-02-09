package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Transient
    public static final String SEQUENCE_NAME = "property_sector_sequence";

    @Id
    @Field("id")
    private String id;
    @Indexed
    @NotBlank
    @Field("name")
    private String name;
    @Field("description")
    private String description;
    private String colorHex;

    @DBRef(lazy = false)
    @NotNull
    private Property property;

    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Field("updated_at")
    private LocalDateTime updatedAt;
}