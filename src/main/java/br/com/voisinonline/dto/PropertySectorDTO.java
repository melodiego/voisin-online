package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertySectorDTO implements Serializable {
    private static final long serialVersionUID = -8359076758421035970L;

    private String id;
    private String name;
    private String description;
    private String colorHex;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}