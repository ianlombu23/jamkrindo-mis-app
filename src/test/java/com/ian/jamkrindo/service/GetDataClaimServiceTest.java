package com.ian.jamkrindo.service;

import com.ian.jamkrindo.exception.CustomException;
import com.ian.jamkrindo.model.projections.LobProjection;
import com.ian.jamkrindo.payload.response.DataClaimResponse;
import com.ian.jamkrindo.repository.primary.LobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetDataClaimServiceTest {

    @Mock
    private LobRepository lobRepository;

    @InjectMocks
    private GetDataClaimService getDataClaimService;

    private List<LobProjection> mockLobProjection;

    @BeforeEach
    void setUp() {
        LobProjection lobProjectionOne = mock(LobProjection.class);
        when(lobProjectionOne.getLobId()).thenReturn(1);
        when(lobProjectionOne.getSubCob()).thenReturn("KBG dan Suretyship");
        when(lobProjectionOne.getClaimReason()).thenReturn("Macet");
        when(lobProjectionOne.getTotalCustomer()).thenReturn(1);
        when(lobProjectionOne.getTotalClaimAmount()).thenReturn(BigDecimal.valueOf(66325000));
        when(lobProjectionOne.getPeriod()).thenReturn(LocalDate.now());
        when(lobProjectionOne.getClaimAmount()).thenReturn(BigDecimal.valueOf(66325000));

        LobProjection lobProjectionTwo = mock(LobProjection.class);
        when(lobProjectionTwo.getLobId()).thenReturn(2);
        when(lobProjectionTwo.getSubCob()).thenReturn("KUR");
        when(lobProjectionTwo.getClaimReason()).thenReturn("Macet");
        when(lobProjectionTwo.getTotalCustomer()).thenReturn(1);
        when(lobProjectionTwo.getTotalClaimAmount()).thenReturn(BigDecimal.valueOf(66325000));
        when(lobProjectionTwo.getPeriod()).thenReturn(LocalDate.now());
        when(lobProjectionTwo.getClaimAmount()).thenReturn(BigDecimal.valueOf(66325000));

        mockLobProjection = List.of(lobProjectionOne, lobProjectionTwo);
    }

    @Test
    void successGetDataClaim() {
        when(lobRepository.getLobDataClaim()).thenReturn(mockLobProjection);
        DataClaimResponse response = getDataClaimService.getDataClaim();
        assertNotNull(response);
        assertEquals(2, response.getTotalCustomerLobClaim());
    }

    @Test
    void failGetDataLob() {
        when(lobRepository.getLobDataClaim()).thenReturn(List.of());
        CustomException exception = assertThrows(CustomException.class, () -> getDataClaimService.getDataClaim());
        assertEquals("5000", exception.getErrorCode());
        assertEquals("Data claim tidak ditemukan", exception.getMessage());
    }
}
