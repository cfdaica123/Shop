package com.diorama.shop.service;

import com.diorama.shop.dto.client.request.AddressRequestDTO;
import com.diorama.shop.dto.client.response.AddressResponseDTO;
import com.diorama.shop.exception.UserNotFoundException;
import com.diorama.shop.mapper.AddressMapper;
import com.diorama.shop.model.Address;
import com.diorama.shop.model.User;
import com.diorama.shop.repository.AddressRepository;
import com.diorama.shop.repository.UserRepository;
import com.diorama.shop.util.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired private AddressRepository addressRepository;
    @Autowired private UserRepository userRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
    }

    public List<AddressResponseDTO> getAllCurrentUserAddresses() {
        User user = getCurrentUser();
        return addressRepository.findByUser(user).stream().map(AddressMapper::toResponseDTO).toList();
    }

    public AddressResponseDTO createAddress(AddressRequestDTO dto) {
        User user = getCurrentUser();

        Address address = AddressMapper.toEntity(dto);
        address.setUser(user);

        if (dto.isDefault()) {
            Address existingDefault = addressRepository.findByUserIdAndIsDefaultTrue(user.getId());
            if (existingDefault != null) {
                existingDefault.setDefault(false);
                addressRepository.save(existingDefault);
            }
        }

        return AddressMapper.toResponseDTO(addressRepository.save(address));
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (dto.isDefault()) {
            Address existingDefault = addressRepository.findByUserIdAndIsDefaultTrue(address.getUser().getId());
            if (existingDefault != null && !existingDefault.getId().equals(address.getId())) {
                existingDefault.setDefault(false);
                addressRepository.save(existingDefault);
            }
        }

        AddressMapper.updateEntityFromDTO(dto, address);
        return AddressMapper.toResponseDTO(addressRepository.save(address));
    }
}