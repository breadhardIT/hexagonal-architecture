package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity.ItemEntity;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.mapper.ItemEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemRepositoryPort {
    final ItemEntityMapper itemEntityMapper;
    final ItemMongoRepository itemMongoRepository;

    @Override
    public List<Item> getItems() {
        return itemMongoRepository.findAll().stream().map(itemEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<Item> getItemById(String id) {
        return itemMongoRepository.findById(id).map(itemEntityMapper::toDomain);
    }

    @Override
    public void deleteItem(String id) {
        itemMongoRepository.deleteById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemMongoRepository.save(itemEntityMapper.fromDomain(item));
    }

    @Override
    public String createItem(Item item) {
        itemMongoRepository.save(itemEntityMapper.fromDomain(item));
        return item.getId();
    }

    @Override
    public Optional<Item> getItemByName(String name) {
        List<ItemEntity> items = itemMongoRepository.findByName(name);
        return items.isEmpty() ? Optional.empty() : items.stream().findFirst().map(itemEntityMapper::toDomain);
    }
}
