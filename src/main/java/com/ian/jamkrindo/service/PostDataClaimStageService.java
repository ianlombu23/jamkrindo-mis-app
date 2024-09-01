package com.ian.jamkrindo.service;

import com.ian.jamkrindo.exception.CustomException;
import com.ian.jamkrindo.model.entity.LogActivity;
import com.ian.jamkrindo.model.entity.stage.LobStage;
import com.ian.jamkrindo.model.enums.Lob;
import com.ian.jamkrindo.model.enums.LogStatus;
import com.ian.jamkrindo.model.projections.LobProjection;
import com.ian.jamkrindo.payload.response.EmptyJsonResponse;
import com.ian.jamkrindo.repository.primary.LobRepository;
import com.ian.jamkrindo.repository.primary.LogActivityRepository;
import com.ian.jamkrindo.repository.stage.LobStageRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostDataClaimStageService {

    private final LobRepository lobRepository;
    private final LobStageRepository lobStageRepository;
    private final LogActivityRepository logActivityRepository;

    @Transactional()
    public EmptyJsonResponse postDataClaim() {
        List<String> subCob = List.of(Lob.KUR.getValue(), Lob.PEN.getValue());
        List<LobProjection> lobs = lobRepository.getLobDataClaimBySubCob(subCob);

        log.info("Start Collect data lob...");
        List<LobStage> lobStages = lobs.stream()
                .map(l ->
                        LobStage.builder()
                                .lobId(l.getLobId())
                                .subCob(l.getSubCob())
                                .claimReason(l.getClaimReason())
                                .period(l.getPeriod())
                                .claimAmount(l.getClaimAmount())
                                .build()
                ).toList();

        try {
            lobStageRepository.saveAll(lobStages);
            log.info("Success insert to db stage, total data: {}", lobStages.size());
            logActivitySave(lobStages.size(), LogStatus.SUCCESS.name());

            return new EmptyJsonResponse();
        } catch (Exception e) {
            log.info("Error inserting to db stage: {}", e.getMessage());
            throw new CustomException(
                    "4000",
                    "Something wrong when insert to db stage",
                    HttpStatus.CONFLICT
            );
        }
    }

    private void logActivitySave(int totalData, String status) {
        logActivityRepository.save(LogActivity.builder()
                .totalData(totalData)
                .status(status)
                .build());
    }
}
