package com.breadhardit.logistics.item.facade.dto.mapper;

import com.breadhardit.logistics.item.business.model.Item;
import com.breadhardit.logistics.item.facade.dto.request.PostItemRequestDTO;
import com.breadhardit.logistics.item.facade.dto.response.GetItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemFacadeMapper {

    @Mapping(source = "id", target = "id")
    GetItemResponseDTO fromDomain(Item in);

    @Mapping(source = "name", target = "name")
    Item fromPostItemRequestDTO(PostItemRequestDTO itemDto);
}



