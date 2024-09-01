CREATE TABLE lob (
  lob_id int NOT NULL AUTO_INCREMENT,
  sub_cob varchar(50) NOT NULL,
  claim_reason varchar(50) NOT NULL,
  period date NOT NULL,
  branch_id int NOT NULL,
  claim_decision date NOT NULL,
  total_guaranteed int NOT NULL,
  claim_amount decimal(10,2) DEFAULT NULL,
  debet_kredit int DEFAULT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NULL DEFAULT NULL,
  PRIMARY KEY (lob_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE log_activity (
  log_activity_id int NOT NULL AUTO_INCREMENT,
  user varchar(50) DEFAULT NULL,
  total_data int NOT NULL,
  proceed_at timestamp NOT NULL,
  status varchar(50)  NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NULL DEFAULT NULL,
  PRIMARY KEY (log_activity_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE branch (
  branch_id int NOT NULL AUTO_INCREMENT,
  branch_name varchar(100) NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NULL DEFAULT NULL,
  PRIMARY KEY (branch_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;