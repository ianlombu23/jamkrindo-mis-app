CREATE TABLE lob_stage (
  lob_stage_id int NOT NULL AUTO_INCREMENT,
  lob_id int NOT NULL,
  sub_cob varchar(50) NOT NULL,
  claim_reason varchar(50) NOT NULL,
  period date NOT NULL,
  claim_amount decimal(10,0) NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NULL DEFAULT NULL,
  PRIMARY KEY (lob_stage_id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;