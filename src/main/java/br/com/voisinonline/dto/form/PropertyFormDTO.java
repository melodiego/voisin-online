package br.com.voisinonline.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyFormDTO implements Serializable {
    private static final long serialVersionUID = 4699268913491499609L;

    @NotBlank(message = "The name could not be null or empty.")
    private String name;

    private String description;
}
