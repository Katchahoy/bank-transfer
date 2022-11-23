package com.bank.bank.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransferResource {

  private final TransferService transferService;

  @Autowired
  public TransferResource(TransferService transferService) {
    this.transferService = transferService;
  }

  @PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> transfer(
          @Valid @RequestBody TransferRequest transferRequest
  ) {
    transferService.transfer(transferRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
