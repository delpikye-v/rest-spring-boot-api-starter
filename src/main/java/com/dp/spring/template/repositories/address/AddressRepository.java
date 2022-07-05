package com.dp.spring.template.repositories.address;

import com.dp.spring.template.models.address.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
