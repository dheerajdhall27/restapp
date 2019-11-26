package com.basic.api.restapp.service;

import com.basic.api.restapp.domain.ObjectModel;
import com.basic.api.restapp.repo.ObjectRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service("IService")
public class ObjectService implements IService {

  private ObjectRepository objectRepository;

  @Autowired
  public ObjectService(ObjectRepository objectRepository) {
    this.objectRepository = objectRepository;
  }

  @Override
  public ObjectModel createObject(Long id, Map<String, String> map) {
    if (!objectRepository.exists(id)) {
      ObjectModel objectModel = new ObjectModel(id);
      objectModel.setMap(map);
      return objectRepository.save(objectModel);
    }

    return null;
  }

  @Override
  public Iterable<ObjectModel> lookup() {
    return objectRepository.findAll();
  }

  @Override
  public ObjectModel findById(Long id) {
    ObjectModel objectModel = objectRepository.findOne(id);

    if (objectModel == null) {
      throw new ResourceAccessException("The Given Id does not match anydata!");
    }

    return objectModel;
  }

  @Override
  public Long total() {
    return objectRepository.count();
  }

  @Override
  public void save(ObjectModel objectModel) {
    objectRepository.save(objectModel);
  }

  @Override
  public Boolean delete(Long id) {
    ObjectModel objectModel = objectRepository.findOne(id);

    if (objectModel != null) {
      objectRepository.delete(id);
      return true;
    }

    return false;
  }
}
