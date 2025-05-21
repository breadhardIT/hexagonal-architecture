package com.breadhardit.logistics.item.infrastructure.rest.dto.request;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Jacksonized
@Getter
@ToString
public class PatchItemRequestDTO {
    final String name;
}
