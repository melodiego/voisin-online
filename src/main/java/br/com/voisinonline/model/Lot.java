package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
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
@Document(value = "lots")
public class Lot implements Serializable {
    private static final long serialVersionUID = -6947803023197083826L;

    @Id
    @Field("id")
    private String id;
    @Indexed
    @Field("name")
    @NotBlank
    private String name;
    @Field("description")
    private String description;
    @DBRef(lazy = false)
    @NotNull
    private PropertySector sector;
    @DBRef(lazy = false)
    @NotNull
    private Category category;
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Field("updated_at")
    private LocalDateTime updatedAt;
}
