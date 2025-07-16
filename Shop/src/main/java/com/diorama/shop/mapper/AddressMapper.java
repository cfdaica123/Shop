package com.diorama.shop.mapper;

import com.diorama.shop.dto.client.request.AddressRequestDTO;
import com.diorama.shop.dto.client.response.AddressResponseDTO;
import com.diorama.shop.model.Address;

public class AddressMapper {
    
    private AddressMapper() {
        // Private constructor to hide implicit public one
    }
    public static Address toEntity(AddressRequestDTO dto) {
        Address address = new Address();
        address.setRecipientName(dto.getRecipientName());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setStreet(dto.getStreet());
        address.setWard(dto.getWard());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setDefault(dto.isDefault());
        return address;
    }

    public static AddressResponseDTO toResponseDTO(Address address) {
        return new AddressResponseDTO(
            address.getId(),
            address.getRecipientName(),
            address.getPhoneNumber(),
            address.getStreet(),
            address.getWard(),
            address.getDistrict(),
            address.getCity(),
            address.isDefault()
        );
    }

    public static void updateEntityFromDTO(AddressRequestDTO dto, Address address) {
        if (dto.getRecipientName() != null) {
            address.setRecipientName(dto.getRecipientName());
        }
        if (dto.getPhoneNumber() != null) {
            address.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getStreet() != null) {
            address.setStreet(dto.getStreet());
        }
        if (dto.getWard() != null) {
            address.setWard(dto.getWard());
        }
        if (dto.getDistrict() != null) {
            address.setDistrict(dto.getDistrict());
        }
        if (dto.getCity() != null) {
            address.setCity(dto.getCity());
        }
        address.setDefault(dto.isDefault());
    }
}