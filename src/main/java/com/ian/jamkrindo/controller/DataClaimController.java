package com.ian.jamkrindo.controller;

import com.ian.jamkrindo.payload.response.DataClaimResponse;
import com.ian.jamkrindo.service.GetDataClaimService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mis/v1/lob")
@AllArgsConstructor
public class DataClaimController {

    private final GetDataClaimService getDataClaimService;

    @GetMapping("/data/claim")
    public DataClaimResponse getDataClaim() {
        return getDataClaimService.getDataClaim();
    }
}
