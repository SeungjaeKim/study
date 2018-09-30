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
  `rss_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `rss_url` VARCHAR(300) NOT NULL COMMENT 'RSS URL',
  `use_yn` VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  `reg_dt` DATE NOT NULL DEFAULT now(),
  `reg_user_id` VARCHAR(45) NULL,
  `upd_dt` DATE NOT NULL DEFAULT now(),
  `upd_user_id` VARCHAR(45) NULL,
  PRIMARY KEY (`rss_id`),
  UNIQUE INDEX `url_UNIQUE` (`rss_url` ASC))
ENGINE = InnoDB
;

-- 뉴스
CREATE TABLE IF NOT EXISTS `study`.`tbl_news` (
  `news_id` INT NOT NULL AUTO_INCREMENT COMMENT '뉴스 고유번호',
  `comp_cd` VARCHAR(45) NULL COMMENT '언론사 코드 - G1',
  `cl_cd` VARCHAR(45) NULL COMMENT '뉴스 분야 코드 - G2',
  `news_title` VARCHAR(200) NULL COMMENT '뉴스 제목',
  `news_content` BLOB NULL COMMENT '뉴스 내용',
  `news_url` VARCHAR(45) NULL COMMENT '뉴스 URL',
  `reg_dt` DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  `upd_dt` DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  PRIMARY KEY (`news_id`))
ENGINE = InnoDB
;
