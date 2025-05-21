package com.breadhardit.logistics.item.application.comand;

import java.util.UUID;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.InvalidItemException;
import com.breadhardit.logistics.item.domain.ItemNotFoundException;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DeleteItemCommand {
    final ItemRepositoryPort itemRepository;
    @NonNull
    final String id;
    public void handle() throws InvalidItemException, ItemNotFoundException {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidItemException();
        }
        if (itemRepository.getItemById(id).isEmpty())
            throw new ItemNotFoundException();
        itemRepository.deleteItem(id);

    }

}
