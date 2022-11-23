package com.bank.bank.transfer;

import com.bank.bank.account.BankAccount;
import com.bank.bank.account.BankAccountRepository;
import com.bank.bank.transaction.Transaction;
import com.bank.bank.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TransferService {

  private final BankAccountRepository bankAccountRepository;
  private final TransactionRepository transactionRepository;

  @Autowired
  public TransferService(
          BankAccountRepository bankAccountRepository,
          TransactionRepository transactionRepository
  ) {
    this.bankAccountRepository = bankAccountRepository;
    this.transactionRepository = transactionRepository;
  }

  @Transactional(rollbackFor = Exception.class)
  public void transfer(
          TransferRequest transferRequest
  ) {
    final BankAccount bankAccount = bankAccountRepository.findBankAccountByBicAndIban(transferRequest.organizationBic(), transferRequest.organizationIban())
            .orElseThrow(() -> new IllegalArgumentException("Source account does not exist in our database"));
    final List<CreditTransfer> creditTransfers = transferRequest.creditTransfers();
    final int totalAmount = transferRequest.amountCents();
    final int accountBalance = Objects.requireNonNullElse(bankAccount.getBalanceCents(), 0);
    if (totalAmount > accountBalance) {
      throw new IllegalArgumentException("Account does not have sufficient funds");
    }
    final List<Transaction> transactions = creditTransfers.stream()
            .map(creditTransfer -> createTransaction(bankAccount, creditTransfer))
            .collect(Collectors.toList());
    transactionRepository.saveAll(transactions);
  }

  private Transaction createTransaction(
          BankAccount bankAccount,
          CreditTransfer creditTransfer
  ) {
    final Transaction transaction = new Transaction();
    transaction.setBankAccount(bankAccount);
    transaction.setCounterpartyName(creditTransfer.counterpartyName());
    transaction.setCounterpartyBic(creditTransfer.counterpartyBic());
    transaction.setCounterpartyIban(creditTransfer.counterPartyIban());
    transaction.setAmountCents(creditTransfer.amountCents());
    transaction.setAmountCurrency(creditTransfer.currency());
    transaction.setDescription(creditTransfer.description());
    return transaction;
  }
}
