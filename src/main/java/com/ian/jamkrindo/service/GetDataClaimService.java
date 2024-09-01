package com.ian.jamkrindo.service;

import com.ian.jamkrindo.exception.CustomException;
import com.ian.jamkrindo.payload.dto.LobDto;
import com.ian.jamkrindo.model.enums.Lob;
import com.ian.jamkrindo.model.projections.LobProjection;
import com.ian.jamkrindo.payload.response.DataClaimResponse;
import com.ian.jamkrindo.payload.response.LobDetailResponse;
import com.ian.jamkrindo.repository.primary.LobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class GetDataClaimService {

    private final LobRepository lobRepository;

    public DataClaimResponse getDataClaim() {
        List<LobProjection> lob = getDataLob();

        Integer totalCustomerLobClaim = lob.stream()
                .mapToInt(LobProjection::getTotalCustomer)
                .sum();

        BigDecimal totalClaimAmount = lob.stream()
                .map(LobProjection::getTotalClaimAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<LobDetailResponse> lobDetailResponses = List.of(
                buildLobDetailResponse(lob, Lob.KBG_SURETYSHIP.getValue()),
                buildLobDetailResponse(lob, Lob.KONSUMTIF.getValue()),
                buildLobDetailResponse(lob, Lob.KUR.getValue()),
                buildLobDetailResponse(lob, Lob.PEN.getValue()),
                buildLobDetailResponse(lob, Lob.PRODUKTIF.getValue())
        );

        return DataClaimResponse.builder()
                .lobs(lobDetailResponses)
                .totalCustomerLobClaim(totalCustomerLobClaim)
                .totalClaimAmount(totalClaimAmount)
                .build();
    }

    private LobDetailResponse buildLobDetailResponse(List<LobProjection> lob, String subCob) {
        Integer subTotalCustomer = lob.stream()
                .filter(l -> isLobSubCobTarget(l, subCob))
                .mapToInt(LobProjection::getTotalCustomer)
                .sum();

        BigDecimal subTotalClaimAmount = lob.stream()
                .filter(l -> isLobSubCobTarget(l, subCob))
                .map(LobProjection::getTotalClaimAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return LobDetailResponse.builder()
                .lob(subCob)
                .subTotalCustomer(subTotalCustomer)
                .subTotalClaimAmount(subTotalClaimAmount)
                .lobClaims(lob.stream()
                        .filter(l -> isLobSubCobTarget(l, subCob))
                        .map(o -> LobDto.builder()
                                .subCob(o.getSubCob())
                                .claimReason(o.getClaimReason())
                                .totalCustomer(o.getTotalCustomer())
                                .claimAmount(o.getTotalClaimAmount())
                                .build())
                        .toList())
                .build();
    }


    private List<LobProjection> getDataLob() {
        List<LobProjection> lob = lobRepository.getLobDataClaim();

        if (lob.isEmpty()) {
            throw new CustomException(
                    "5000",
                    "Data claim tidak ditemukan",
                    HttpStatus.CONFLICT
            );
        }

        return lob;
    }

    private boolean isLobSubCobTarget(LobProjection lob, String lobSubCob) {
        return StringUtils.equalsAnyIgnoreCase(lob.getSubCob(), lobSubCob);
    }
}
