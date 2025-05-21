package com.breadhardit.logistics.item.repository;

import java.util.List;

import com.breadhardit.logistics.item.repository.entity.ItemEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<ItemEntity, String> {
    List<ItemEntity> findByName(String name);
}
