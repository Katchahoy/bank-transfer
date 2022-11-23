package com.bank.bank.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public record TransferRequest(
        @NotBlank
        @Size(max = 64)
        @JsonProperty("organization_name")
        String organizationName,

        @NotBlank
        @Size(max = 11)
        @JsonProperty("organization_bic")
        String organizationBic,

        @NotBlank
        @Size(max = 34)
        @JsonProperty("organization_iban")
        String organizationIban,

        @NotEmpty
        @JsonProperty("credit_transfers")
        List<CreditTransfer> creditTransfers
) {
  public int amountCents() {
    return creditTransfers.stream().mapToInt(CreditTransfer::amountCents).sum();
  }
}

