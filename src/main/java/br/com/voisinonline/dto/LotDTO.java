package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotDTO implements Serializable {
    private static final long serialVersionUID = -6071015991903657827L;

    private String id;
    private String name;
    private String description;
    private CategoryDTO category;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}