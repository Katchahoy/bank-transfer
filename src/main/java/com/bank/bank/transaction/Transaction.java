package com.bank.bank.transaction;

import com.bank.bank.account.BankAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Integer id;

  @Column(name = "counterparty_name")
  String counterpartyName;

  @Column(name = "counterparty_iban")
  String counterpartyIban;

  @Column(name = "counterparty_bic")
  String counterpartyBic;

  @Column(name = "amount_cents")
  Integer amountCents;

  @Column(name = "amount_currency")
  String amountCurrency;

  @ManyToOne
  @JoinColumn(name = "bank_account_id")
  BankAccount bankAccount;

  @Column(name = "description")
  String description;
}
