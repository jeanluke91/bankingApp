package it.dantonio.bankingapp.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class LegalPersonBeneficiary implements Serializable {

    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

}
