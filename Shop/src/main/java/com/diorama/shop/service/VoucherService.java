package com.diorama.shop.service;

import com.diorama.shop.dto.client.request.VoucherRequestDTO;
import com.diorama.shop.dto.client.response.VoucherResponseDTO;
import com.diorama.shop.mapper.VoucherMapper;
import com.diorama.shop.model.Voucher;
import com.diorama.shop.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    public VoucherResponseDTO create(VoucherRequestDTO dto) {
        Voucher voucher = VoucherMapper.toEntity(dto);
        return VoucherMapper.toResponseDTO(voucherRepository.save(voucher));
    }

    public VoucherResponseDTO getById(Long id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher not found"));
        return VoucherMapper.toResponseDTO(voucher);
    }

    public List<VoucherResponseDTO> getAll() {
        return voucherRepository.findAll().stream()
                .map(VoucherMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        voucherRepository.deleteById(id);
    }

    public VoucherResponseDTO update(Long id, VoucherRequestDTO dto) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher not found"));

        voucher.setCode(dto.getCode());
        voucher.setDiscountType(dto.getDiscountType());
        voucher.setDiscountValue(dto.getDiscountValue());
        voucher.setMaxDiscountAmount(dto.getMaxDiscountAmount());
        voucher.setMinOrderValue(dto.getMinOrderValue());
        voucher.setStartDate(dto.getStartDate());
        voucher.setEndDate(dto.getEndDate());

        return VoucherMapper.toResponseDTO(voucherRepository.save(voucher));
    }

    public Optional<Voucher> findByCode(String code) {
        return voucherRepository.findByCode(code);
    }
}