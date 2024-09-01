package com.ian.jamkrindo.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class LobDto {
    private String subCob;
    private String claimReason;
    private Integer totalCustomer;
    private BigDecimal claimAmount;
}
