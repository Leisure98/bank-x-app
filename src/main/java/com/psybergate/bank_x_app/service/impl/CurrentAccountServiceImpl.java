package com.psybergate.bank_x_app.service.impl;

import com.psybergate.bank_x_app.domain.account.CurrentAccount;
import com.psybergate.bank_x_app.exception.AccountException;
import com.psybergate.bank_x_app.repository.CurrentAccountRepository;
import com.psybergate.bank_x_app.service.CurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("currentAccountService")
@Transactional
@SuppressWarnings("unused")
public class CurrentAccountServiceImpl implements CurrentAccountService {

  private final CurrentAccountRepository currentAccountRepository;

  @Autowired
  public CurrentAccountServiceImpl(CurrentAccountRepository currentAccountRepository) {
    this.currentAccountRepository = currentAccountRepository;
  }

  @Override
  public CurrentAccount create(CurrentAccount currentAccount) {
    return currentAccountRepository.save(currentAccount);
  }

  @Override
  public void update(CurrentAccount currentAccount) {
    currentAccountRepository.save(currentAccount);
  }

  @Override
  public void delete(Long accountNum) {
    CurrentAccount currentAccount = findById(accountNum);
    if (currentAccount == null) {
      throw new AccountException("Account doesn't exist");
    }

    //TODO: Change to soft delete
    //Does a hard delete. Soft Delete is recommended where the data is kept but the user doesn't
    // have access to it.
    currentAccountRepository.delete(currentAccount);
  }

  @Override
  public CurrentAccount findById(Long accountNum) {
    Optional<CurrentAccount> currentAccount = currentAccountRepository.findById(accountNum);
    return currentAccount.orElse(null);
  }

  @Override
  public List<CurrentAccount> findAll() {
    return currentAccountRepository.findAll();
  }

  @Override
  public List<CurrentAccount> findByCustomerId(Long customerNum) {
    return currentAccountRepository.findByCustomerCustomerNumEquals(customerNum);
  }
}