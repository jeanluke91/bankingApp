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
public class Account implements Serializable {

    @NotNull(message = "accountCode is mandatory")
    private String accountCode;
    private String bicCode;

}
