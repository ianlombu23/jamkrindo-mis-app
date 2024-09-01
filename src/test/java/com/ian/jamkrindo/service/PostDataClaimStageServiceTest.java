package com.ian.jamkrindo.service;

import com.ian.jamkrindo.exception.CustomException;
import com.ian.jamkrindo.model.projections.LobProjection;
import com.ian.jamkrindo.payload.response.EmptyJsonResponse;
import com.ian.jamkrindo.repository.primary.LobRepository;
import com.ian.jamkrindo.repository.primary.LogActivityRepository;
import com.ian.jamkrindo.repository.stage.LobStageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostDataClaimStageServiceTest {

    @Mock
    private LobRepository lobRepository;

    @Mock
    private LobStageRepository lobStageRepository;

    @Mock
    private LogActivityRepository logActivityRepository;

    @InjectMocks
    private PostDataClaimStageService postDataClaimStageService;

    private List<LobProjection> mockLobProjection;

    @BeforeEach
    void setUp() {
        LobProjection lobProjectionOne = mock(LobProjection.class);
        when(lobProjectionOne.getLobId()).thenReturn(1);
        when(lobProjectionOne.getSubCob()).thenReturn("PEN");
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
    void successPostDataClaim() {
        when(lobRepository.getLobDataClaimBySubCob(any())).thenReturn(mockLobProjection);
        EmptyJsonResponse response = postDataClaimStageService.postDataClaim();
        assertNotNull(response);
        verify(lobStageRepository, times(1)).saveAll(anyList());
        verify(logActivityRepository, times(1)).save(any());
    }

    @Test
    void failPostDataClaim() {
        when(lobRepository.getLobDataClaimBySubCob(any())).thenReturn(mockLobProjection);
        doThrow(new RuntimeException("DB error")).when(lobStageRepository).saveAll(anyList());
        CustomException exception = assertThrows(CustomException.class, () -> postDataClaimStageService.postDataClaim());

       assertEquals("4000", exception.getErrorCode());
       assertEquals("Something wrong when insert to db stage", exception.getMessage());
       assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }


}
