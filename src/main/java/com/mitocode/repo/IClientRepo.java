package com.mitocode.repo;

import com.mitocode.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepo extends IGenericRepo<Client, Integer> {
}
