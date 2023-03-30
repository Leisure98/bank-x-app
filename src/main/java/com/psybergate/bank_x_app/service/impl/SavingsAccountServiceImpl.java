package com.psybergate.bank_x_app.service.impl;

import com.psybergate.bank_x_app.domain.account.SavingsAccount;
import com.psybergate.bank_x_app.exception.AccountException;
import com.psybergate.bank_x_app.repository.SavingsAccountRepository;
import com.psybergate.bank_x_app.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("savingsAccountService")
@Transactional
@SuppressWarnings("unused")
public class SavingsAccountServiceImpl implements SavingsAccountService {

  private final SavingsAccountRepository savingsAccountRepository;

  @Autowired
  public SavingsAccountServiceImpl(SavingsAccountRepository savingsAccountRepository) {
    this.savingsAccountRepository = savingsAccountRepository;
  }

  @Override
  public SavingsAccount create(SavingsAccount savingsAccount) {
    return savingsAccountRepository.save(savingsAccount);
  }

  @Override
  public void update(SavingsAccount savingsAccount) {
    savingsAccountRepository.save(savingsAccount);
  }

  @Override
  public void delete(Long accountNum) {
    SavingsAccount savingsAccount = findById(accountNum);
    if (savingsAccount == null) {
      throw new AccountException("Account doesn't exist");
    }

    //TODO: Change to soft delete
    //Does a hard delete. Soft Delete is recommended where the data is kept but the user doesn't
    // have access to it.
    savingsAccountRepository.delete(savingsAccount);
  }

  @Override
  public SavingsAccount findById(Long accountNum) {
    Optional<SavingsAccount> savingsAccount = savingsAccountRepository.findById(accountNum);
    return savingsAccount.orElse(null);
  }

  @Override
  public List<SavingsAccount> findAll() {
    return savingsAccountRepository.findAll();
  }

  @Override
  public List<SavingsAccount> findByCustomerId(Long customerNum) {
    return savingsAccountRepository.findByCustomerCustomerNumEquals(customerNum);
  }
}
