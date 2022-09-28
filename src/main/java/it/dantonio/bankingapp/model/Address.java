package it.dantonio.bankingapp.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class Address implements Serializable {

  private String address;
  private String city;
  private String countryCode;
}
