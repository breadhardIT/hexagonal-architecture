package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.ItemJpaRepository;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity.ItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryAdapter implements ItemRepositoryPort {

    private final ItemJpaRepository itemRepository;  // MongoDB Repository

    @Autowired
    public ItemRepositoryAdapter(ItemJpaRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll().stream()
                .map(this::toDomain)  // Convert MongoDB Entity to Domain
                .toList();
    }

    @Override
    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(fromDomain(item));  // Save the Domain object to MongoDB
    }

    @Override
    public String createItem(Item item) {
        itemRepository.save(fromDomain(item));  // Save the Domain object to MongoDB
        return item.getId();
    }

    @Override
    public Optional<Item> getItemByName(String name) {
        return itemRepository.findByName(name).stream()
                .findFirst()
                .map(this::toDomain);
    }

    // Convert ItemEntity (MongoDB) to Item (Domain)
    private Item toDomain(ItemEntity itemEntity) {
        return new Item(itemEntity.getId(), itemEntity.getName());
    }

    // Convert Item (Domain) to ItemEntity (MongoDB)
    private ItemEntity fromDomain(Item item) {
        return new ItemEntity(item.getId(), item.getName());
    }
}
