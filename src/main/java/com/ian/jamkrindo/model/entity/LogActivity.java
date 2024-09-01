package com.ian.jamkrindo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_activity")
public class LogActivity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="log_activity_id")
    private int lobActivityId;

    @Column(name="user")
    private String user;

    @Column(name="total_data")
    private Integer totalData;

    @Column(name="proceed_at")
    private LocalDateTime proceedAt;

    @Column(name="status")
    private String status;


    @PrePersist
    void onCreate() {
        this.user = "SYSTEM";
        this.proceedAt = LocalDateTime.now();
    }
}
