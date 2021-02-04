package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String name;
    private String description;
    private String colorHex;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}