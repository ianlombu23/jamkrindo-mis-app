package com.ian.jamkrindo.model.entity;

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
@Table(name = "lob")
public class Lob extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lob_id")
    private int lobId;

    @Column(name="sub_cob")
    private String subCob;

    @Column(name="claim_reason")
    private String claimReason;

    @Column(name="period")
    private LocalDate period;

    @Column(name="branch_id")
    private String branchId;

    @Column(name="claim_decision")
    private String claimDecision;

    @Column(name="total_guaranteed")
    private Integer totalGuaranteed;

    @Column(name="claim_amount")
    private BigDecimal claimAmount;

    @Column(name="debet_kredit")
    private Integer debetKredit;
    
}
