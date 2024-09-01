package com.ian.jamkrindo.controller;

import com.ian.jamkrindo.payload.response.EmptyJsonResponse;
import com.ian.jamkrindo.service.PostDataClaimStageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mis/v1/lob-stage")
@AllArgsConstructor
public class LobStageController {

    private final PostDataClaimStageService postDataClaimStageService;

    @PostMapping("/integrate")
    public EmptyJsonResponse upsertLobStage() {
        return postDataClaimStageService.postDataClaim();
    }
}
