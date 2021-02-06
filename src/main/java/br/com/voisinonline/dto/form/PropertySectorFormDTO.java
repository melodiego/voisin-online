package br.com.voisinonline.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertySectorFormDTO implements Serializable {
    private static final long serialVersionUID = -2316114613668154022L;

    @NotBlank(message = "The name could not be null or empty.")
    private String name;
    @NotBlank(message = "The propertyId could not be null or empty.")
    private String propertyId;
    @NotBlank(message = "The colorHex could not be null or empty.")
    private String colorHex;
    private String description;
}