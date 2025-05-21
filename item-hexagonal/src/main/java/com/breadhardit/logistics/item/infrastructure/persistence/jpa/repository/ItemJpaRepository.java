package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository;

import java.util.List;

import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity.ItemEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemJpaRepository extends MongoRepository<ItemEntity, String> {
    List<ItemEntity> findByName(String name);
}
