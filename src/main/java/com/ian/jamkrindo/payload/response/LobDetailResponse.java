package com.ian.jamkrindo.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ian.jamkrindo.payload.dto.LobDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LobDetailResponse {
    private String lob;
    private Integer subTotalCustomer;
    private BigDecimal subTotalClaimAmount;
    private List<LobDto> lobClaims;
}
