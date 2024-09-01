package com.ian.jamkrindo.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataClaimResponse {
    private Integer totalCustomerLobClaim;
    private BigDecimal totalClaimAmount;
    private List<LobDetailResponse> lobs;
}
