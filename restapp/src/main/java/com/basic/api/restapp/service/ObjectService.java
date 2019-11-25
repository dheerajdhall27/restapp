package com.basic.api.restapp.service;

import com.basic.api.restapp.domain.ObjectModel;
import com.basic.api.restapp.repo.ObjectRepository;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectService {

  private ObjectRepository objectRepository;

  @Autowired
  public ObjectService(ObjectRepository objectRepository) {
    this.objectRepository = objectRepository;
  }

  public ObjectModel createObject(Long id, Map<String, String> map) {
    if (!objectRepository.existsById(id)) {
      ObjectModel objectModel = new ObjectModel(id);
      objectModel.setMap(map);
      return objectRepository.save(objectModel);
    }

    return null;
  }

  public Iterable<ObjectModel> lookup() {
    return objectRepository.findAll();
  }

  public long total() {
    return objectRepository.count();
  }
}
