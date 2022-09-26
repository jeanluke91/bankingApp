package it.dantonio.bankingapp.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class LegalPersonBeneficiary implements Serializable {

    @NotNull(message = "LegalPersonBeneficiary fiscalcode is mandatory")
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

}
