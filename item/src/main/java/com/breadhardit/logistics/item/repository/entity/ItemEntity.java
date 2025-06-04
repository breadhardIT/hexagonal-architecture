package com.breadhardit.logistics.item.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Document
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ItemEntity {
    @Id
    private String id;
    private String name;

}
