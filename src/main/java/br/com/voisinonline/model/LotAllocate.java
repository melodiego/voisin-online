package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "lot_allocate")
public class LotAllocate implements Serializable {
    private static final long serialVersionUID = -5682635962294673158L;

    @Id
    @Field("id")
    private String id;
    @DBRef(lazy = false)
    @NotNull
    private Lot lot;
    @DBRef(lazy = false)
    @NotNull
    private PropertySector sector;
    @DBRef(lazy = false)
    @NotNull
    private Picket picket;
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Field("updated_at")
    private LocalDateTime updatedAt;
}