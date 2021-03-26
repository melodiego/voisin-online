package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PicketDTO implements Serializable {
    private static final long serialVersionUID = -6071015991903657827L;

    private String id;
    private String name;
    private String description;
    private Double picketArea;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}