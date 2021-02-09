package br.com.voisinonline.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City implements Serializable {
    private static final long serialVersionUID = 3041993370309973365L;

    @Field("code_ibge")
    private String codeIbge;
    @NotBlank
    @Field("name")
    private String name;
}