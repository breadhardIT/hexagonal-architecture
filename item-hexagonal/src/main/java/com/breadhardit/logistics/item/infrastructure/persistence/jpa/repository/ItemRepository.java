package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository;

import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity.ItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<ItemEntity, String> {
    List<ItemEntity> findByName(String name);
}