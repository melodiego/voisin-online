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
public class LotFormDTO implements Serializable {
    private static final long serialVersionUID = 5854418911597744473L;

    @NotBlank(message = "The name could not be null or empty.")
    private String name;
    @NotBlank(message = "The sector id could not be null or empty.")
    private String propertySectorId;
    @NotNull(message = "The category id could not be null or empty.")
    private Long categoryId;
    private String description;
}