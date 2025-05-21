package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity;

import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ItemEntity {
    @Id
    private String id;
    private String name;
}