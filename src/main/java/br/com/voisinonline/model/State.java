package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "state")
public class State implements Serializable {
    private static final long serialVersionUID = 5783311217454483955L;

    @Transient
    public static final String SEQUENCE_NAME = "state_sequence";

    @Id
    @Field("id")
    private Long id;
    @Indexed
    @NotBlank
    @Field("name")
    private String name;
    @Field("uf")
    private String uf;
    @Field("cities")
    private List<City> cities;
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Field("updated_at")
    private LocalDateTime updatedAt;
}