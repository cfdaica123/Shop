package com.diorama.shop.mapper;

import com.diorama.shop.dto.client.request.VoucherRequestDTO;
import com.diorama.shop.dto.client.response.VoucherResponseDTO;
import com.diorama.shop.model.Voucher;

public class VoucherMapper {
    
    private VoucherMapper() {
        // Private constructor to hide implicit public one
    }
    public static Voucher toEntity(VoucherRequestDTO dto) {
        Voucher voucher = new Voucher();
        voucher.setCode(dto.getCode());
        voucher.setDiscountType(dto.getDiscountType());
        voucher.setDiscountValue(dto.getDiscountValue());
        voucher.setMaxDiscountAmount(dto.getMaxDiscountAmount());
        voucher.setMinOrderValue(dto.getMinOrderValue());
        voucher.setStartDate(dto.getStartDate());
        voucher.setEndDate(dto.getEndDate());
        voucher.setUsageLimit(dto.getUsageLimit());
        return voucher;
    }

    public static VoucherResponseDTO toResponseDTO(Voucher voucher) {
        return new VoucherResponseDTO(
            voucher.getId(),
            voucher.getCode(),
            voucher.getDiscountType(),
            voucher.getDiscountValue(),
            voucher.getMaxDiscountAmount(),
            voucher.getMinOrderValue(),
            voucher.getStartDate(),
            voucher.getEndDate(),
            voucher.getUsageLimit(),
            voucher.getCurrentUsage()
        );
    }
}
