package com.ian.jamkrindo.model.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LobProjection {
    int getLobId();
    String getSubCob();
    String getClaimReason();
    Integer getTotalCustomer();
    BigDecimal getTotalClaimAmount();
    LocalDate getPeriod();
    BigDecimal getClaimAmount();
}
