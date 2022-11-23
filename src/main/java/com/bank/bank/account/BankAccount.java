package com.bank.bank.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankAccount {

  @Id
  @Column(name = "id")
  Integer id;

  @Column(name = "organization_name")
  String organizationName;

  @Column(name = "balance_cents")
  Integer balanceCents;

  @Column(name = "iban")
  String iban;

  @Column(name = "bic")
  String bic;
}
