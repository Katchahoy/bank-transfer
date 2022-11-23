package com.bank.bank.account;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends PagingAndSortingRepository<BankAccount, Integer> {

  Optional<BankAccount> findBankAccountByBicAndIban(String bic, String iban);
}
