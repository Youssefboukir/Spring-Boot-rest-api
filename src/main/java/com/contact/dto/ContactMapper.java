package com.contact.dto;

import com.contact.entity.ContactEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto toContactDto(ContactEntity contact);

    List<ContactDto> toContactDtos(List<ContactEntity> contacts);

    ContactEntity toContact(ContactDto contactDto);
}
