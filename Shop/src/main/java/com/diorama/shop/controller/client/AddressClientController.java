package com.diorama.shop.controller.client;

import com.diorama.shop.dto.client.request.AddressRequestDTO;
import com.diorama.shop.dto.client.response.AddressResponseDTO;
import com.diorama.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressClientController {

    @Autowired private AddressService addressService;

    @GetMapping
    public List<AddressResponseDTO> getMyAddresses() {
        return addressService.getAllCurrentUserAddresses();
    }

    @PostMapping
    public AddressResponseDTO create(@RequestBody AddressRequestDTO dto) {
        return addressService.createAddress(dto);
    }

    @PutMapping("/{id}")
    public AddressResponseDTO update(@PathVariable Long id, @RequestBody AddressRequestDTO dto) {
        return addressService.updateAddress(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}  