package br.com.voisinonline.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryFormDTO implements Serializable {
    private static final long serialVersionUID = 3912062851306292273L;

    @NotBlank(message = "The name could not be null or empty.")
    private String name;
    private String description;
}