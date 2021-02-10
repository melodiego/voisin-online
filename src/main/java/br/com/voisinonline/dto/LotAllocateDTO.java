package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotAllocateDTO implements Serializable {
    private static final long serialVersionUID = 8706107142409090426L;

    private String id;
    private LotDTO lot;
    private PropertySectorDTO sector;
    private PicketDTO picket;
}