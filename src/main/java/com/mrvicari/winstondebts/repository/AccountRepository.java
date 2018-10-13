package com.mrvicari.winstondebts.repository;

import com.mrvicari.winstondebts.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(String username);
}
