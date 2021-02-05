package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    @Id
    private String id;
    @Indexed
    private String name;
    private String description;
    private Long totalPastureArea;
    //TODO: Adicionar Cidade/AL
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @DBRef
    private List<PropertySector> sectors;
}