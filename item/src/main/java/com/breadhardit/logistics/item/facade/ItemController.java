package com.breadhardit.logistics.item.facade;

import java.util.List;
import java.util.Optional;

import com.breadhardit.logistics.item.business.InvalidItemException;
import com.breadhardit.logistics.item.business.ItemNotFoundException;
import com.breadhardit.logistics.item.business.ItemService;
import com.breadhardit.logistics.item.business.model.Item;
import com.breadhardit.logistics.item.facade.dto.mapper.ItemFacadeMapper;
import com.breadhardit.logistics.item.facade.dto.request.PatchItemRequestDTO;
import com.breadhardit.logistics.item.facade.dto.request.PostItemRequestDTO;
import com.breadhardit.logistics.item.facade.dto.response.GetItemResponseDTO;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    final ItemService itemService;
    final ItemFacadeMapper itemFacadeMapper;
    @GetMapping
    public ResponseEntity getAllItems() {
        List<Item> items = itemService.getAllItems();
        if (items.isEmpty()) return ResponseEntity.noContent().build();
        List<GetItemResponseDTO> itemsResponse = items.stream().map(itemFacadeMapper::fromDomain).toList();
        return ResponseEntity.ok(itemsResponse);
    }

    @PostMapping
    public ResponseEntity createItem(@RequestBody PostItemRequestDTO itemDto) {
        try {
            String id = itemService.createItem(itemFacadeMapper.fromPostItemRequestDTO(itemDto));
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (InvalidItemException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getItemById(@PathVariable String id) {
        Optional<Item> item = itemService.getItemById(id);
        return item.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(item.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateItem(
            @PathVariable String id,
            @RequestBody PatchItemRequestDTO itemDto) {
        Optional<Item> item = itemService.getItemById(id);
        if (item.isEmpty())
            return ResponseEntity.notFound().build();
        try {
            itemService.updateItem(item.get().withName(itemDto.getName()));
        } catch (InvalidItemException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable String id) {
        try {
            itemService.deleteItem(id);
        } catch (InvalidItemException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
