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
public class Creditor implements Serializable {

    private String name;

    @NotNull(message = "Account is mandatory")
    private Account account;

    private Address address;
}
