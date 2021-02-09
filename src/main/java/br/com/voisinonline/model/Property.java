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
@Document(value = "property")
public class Property implements Serializable {
    private static final long serialVersionUID = -5864520625219015462L;
//
//    @Transient
//    public static final String SEQUENCE_NAME = "property_sequence";

    @Id
    @Field("id")
    private String id;
    @Indexed
    @Field("name")
    @NotBlank
    private String name;
    @Field("description")
    private String description;
    @Field("total_pasture_area")
    private Long totalPastureArea;
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Field("updated_at")
    private LocalDateTime updatedAt;
    @Field("city")
    private City city;
}