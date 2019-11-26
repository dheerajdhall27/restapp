package com.basic.api.restapp.repo;

import com.basic.api.restapp.domain.ObjectModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends CrudRepository<ObjectModel, Long> {

}
