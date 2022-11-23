package com.bank.bank.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record CreditTransfer(
        @NotBlank
        @Size(max = 32)
        @JsonProperty("amount")
        BigDecimal amount,

        @NotNull
        @Size(min = 3, max = 3)
        @Pattern(regexp = "[A-Z]{3}")
        @JsonProperty("currency")
        String currency,

        @NotBlank
        @Size(max = 64)
        @JsonProperty("counterparty_name")
        String counterpartyName,

        @NotBlank
        @Size(max = 11)
        @JsonProperty("counterparty_bic")
        String counterpartyBic,

        @NotBlank
        @Size(max = 34)
        @JsonProperty("counterparty_iban")
        String counterPartyIban,

        @NotBlank
        @Size(max = 256)
        @JsonProperty("description")
        String description
) {
  public int amountCents() {
    return amount.movePointRight(2).intValue();
  }
}
