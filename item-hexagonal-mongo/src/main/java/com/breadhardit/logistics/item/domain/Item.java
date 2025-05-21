package com.breadhardit.logistics.item.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Item {
    @Builder.Default
    final String id = UUID.randomUUID().toString();
    @With
    String name;
}
