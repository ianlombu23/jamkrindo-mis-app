package com.ian.jamkrindo.repository.primary;

import com.ian.jamkrindo.model.entity.Lob;
import com.ian.jamkrindo.model.projections.LobProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LobRepository extends JpaRepository<Lob, Integer> {

    @Query(value =
            "SELECT l.sub_cob AS subCob, " +
                    "l.claim_reason AS claimReason, " +
                    "COUNT(*) AS totalCustomer, " +
                    "SUM(l.claim_amount) AS totalClaimAmount " +
                    "FROM lob l " +
                    "GROUP BY l.sub_cob, l.claim_reason",
            nativeQuery = true
    )
    List<LobProjection> getLobDataClaim();

    @Query(value =
            "SELECT l.lob_id AS lobId, " +
                    "l.sub_cob AS subCob, " +
                    "l.claim_reason AS claimReason, " +
                    "l.period AS period, " +
                    "l.claim_amount AS claimAmount " +
                    "FROM lob l " +
                    "WHERE l.sub_cob in (:subCob)",
            nativeQuery = true
    )
    List<LobProjection> getLobDataClaimBySubCob(List<String> subCob);
}
