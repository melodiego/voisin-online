package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO implements Serializable {
    private static final long serialVersionUID = 5244823371989299095L;

    private String id;
    private String name;
    private String description;
}