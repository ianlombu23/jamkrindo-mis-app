package com.ian.jamkrindo.model.entity.stage;

import com.ian.jamkrindo.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lob_stage")
public class LobStage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lob_stage_id")
    private int lobStageId;

    @Column(name="lob_id")
    private int lobId;

    @Column(name="sub_cob")
    private String subCob;

    @Column(name="claim_reason")
    private String claimReason;

    @Column(name="period")
    private LocalDate period;

    @Column(name="claim_amount")
    private BigDecimal claimAmount;
    
}
