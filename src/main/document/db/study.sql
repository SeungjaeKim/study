/* ****************************************************
 * 테이블
 * ***************************************************/

-- 공통코드그룹
CREATE TABLE IF NOT EXISTS `study`.`tbl_comm_code_group` (
  `code_group_id` VARCHAR(45) NOT NULL COMMENT '코드 그룹 아이디',
  `code_group_nm` VARCHAR(45) NULL COMMENT '코드 그룹 명',
  `code_group_dc` VARCHAR(45) NULL COMMENT '코드 그룹 설명',
  `use_yn` VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  `del_yn` VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부 YN',
  `reg_dt` DATE NOT NULL DEFAULT now() COMMENT '등록 일시',
  `reg_user_id` VARCHAR(45) NULL COMMENT '등록 사용자 아이디',
  `upd_dt` DATE NOT NULL DEFAULT now() COMMENT '수정 일시',
  `upd_user_id` VARCHAR(45) NULL COMMENT '수정 사용자 아이디',
  PRIMARY KEY (`code_group_id`))
ENGINE = InnoDB
;

-- 공통코드
CREATE TABLE IF NOT EXISTS `study`.`tbl_comm_code` (
  `code_group_id` VARCHAR(45) NOT NULL COMMENT '코드 그룹 아이디',
  `code_id` VARCHAR(45) NOT NULL COMMENT '코드 아이디',
  `code_nm` VARCHAR(45) NULL COMMENT '코드 명',
  `code_dc` VARCHAR(45) NULL COMMENT '코드 설명',
  `use_yn` VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  `del_yn` VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부 YN',
  `reg_dt` DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  `reg_user_id` VARCHAR(45) NULL COMMENT '등록 사용자 아이디',
  `upd_dt` DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  `upd_user_id` VARCHAR(45) NULL COMMENT '수정 사용자 아이디',
  PRIMARY KEY (`code_group_id`, `code_id`),
  CONSTRAINT `fk_tbl_comm_code_tbl_comm_code_group1`
    FOREIGN KEY (`code_group_id`)
    REFERENCES `study`.`tbl_comm_code_group` (`code_group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;

-- 뉴스 RSS
CREATE TABLE IF NOT EXISTS `study`.`tbl_news_rss` (
  `rss_seq` BIGINT(20) NOT NULL COMMENT '일련번호',
  `comp_cd` VARCHAR(45) NULL COMMENT '언론사 코드 - G1',
  `cl_cd` VARCHAR(45) NULL COMMENT '뉴스 분야 코드 - G2',
  `rss_url` VARCHAR(300) NOT NULL COMMENT 'RSS URL',
  `use_yn` VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  `last_build_date` VARCHAR(100) NULL COMMENT '마지막 작성 일시',
  `reg_dt` DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  `reg_user_id` VARCHAR(45) NULL COMMENT '등록 사용자 아이디',
  `upd_dt` DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  `upd_user_id` VARCHAR(45) NULL COMMENT '수정 사용자 아이디',
  PRIMARY KEY (`rss_seq`),
  UNIQUE INDEX `url_UNIQUE` (`rss_url` ASC))
ENGINE = InnoDB
;

-- 뉴스
CREATE TABLE IF NOT EXISTS `study`.`tbl_news` (
  `news_seq` INT NOT NULL AUTO_INCREMENT COMMENT '뉴스 고유번호',
  `comp_cd` VARCHAR(45) NULL COMMENT '언론사 코드 - G1',
  `cl_cd` VARCHAR(45) NULL COMMENT '뉴스 분야 코드 - G2',
  `news_title` VARCHAR(200) NULL COMMENT '뉴스 제목',
  `news_content` BLOB NULL COMMENT '뉴스 내용',
  `news_url` VARCHAR(45) NULL COMMENT '뉴스 URL',
  `reg_dt` DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  `upd_dt` DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  PRIMARY KEY (`news_seq`))
ENGINE = InnoDB
;

/* ****************************************************
 * 공통코드
 * ***************************************************/

-- 뉴스 언론사
INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G1', 'G1C1', '한겨레', null, 'Y', 'N', NOW(), null, NOW(), null);

-- 뉴스 분야
INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C1', '정치', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C2', '경제', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C3', '사회', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C4', '국제', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C5', '대중문화', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C6', '스포츠', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C7', '과학', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C8', '사설/칼럼', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C9', '만화만평', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C10', '한겨레섹션', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C11', '주요기사', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', 'G2C12', '인기기사', null, 'Y', 'N', NOW(), null, NOW(), null);