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
@Document(value = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 3979905557978335964L;

    @Transient
    public static final String SEQUENCE_NAME = "category_sequence";

    @Id
    @Field("id")
    private Long id;
    @Indexed
    @Field("name")
    @NotBlank
    private String name;
    @Field("description")
    private String description;
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Field("updated_at")
    private LocalDateTime updatedAt;
}