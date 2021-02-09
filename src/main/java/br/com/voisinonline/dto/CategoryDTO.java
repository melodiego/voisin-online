package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 8850462057268865704L;

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}