package com.diorama.shop.controller.admin;

import com.diorama.shop.dto.client.request.VoucherRequestDTO;
import com.diorama.shop.dto.client.response.VoucherResponseDTO;
import com.diorama.shop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @PostMapping
    public VoucherResponseDTO create(@RequestBody VoucherRequestDTO dto) {
        return voucherService.create(dto);
    }

    @GetMapping("/{id}")
    public VoucherResponseDTO get(@PathVariable Long id) {
        return voucherService.getById(id);
    }

    @GetMapping
    public List<VoucherResponseDTO> getAll() {
        return voucherService.getAll();
    }

    @PutMapping("/{id}")
    public VoucherResponseDTO update(@PathVariable Long id, @RequestBody VoucherRequestDTO dto) {
        return voucherService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        voucherService.delete(id);
    }
}
