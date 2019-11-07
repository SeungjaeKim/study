/* ****************************************************
 * 테이블
 * ***************************************************/

-- 공통코드그룹
CREATE TABLE IF NOT EXISTS 'study'.'tbl_comm_code_group' (
  'code_group_id' VARCHAR(45) NOT NULL COMMENT '코드 그룹 아이디',
  'code_group_nm' VARCHAR(45) NULL COMMENT '코드 그룹 명',
  'code_group_dc' VARCHAR(45) NULL COMMENT '코드 그룹 설명',
  'use_yn' VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  'del_yn' VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부 YN',
  'reg_dt' DATE NOT NULL DEFAULT now() COMMENT '등록 일시',
  'reg_user_id' VARCHAR(45) NULL COMMENT '등록 사용자 아이디',
  'upd_dt' DATE NOT NULL DEFAULT now() COMMENT '수정 일시',
  'upd_user_id' VARCHAR(45) NULL COMMENT '수정 사용자 아이디',
  PRIMARY KEY ('code_group_id'))
ENGINE = InnoDB
;

-- 공통코드
CREATE TABLE IF NOT EXISTS 'study'.'tbl_comm_code' (
  'code_group_id' VARCHAR(45) NOT NULL COMMENT '코드 그룹 아이디',
  'code_id' VARCHAR(45) NOT NULL COMMENT '코드 아이디',
  'code_nm' VARCHAR(45) NULL COMMENT '코드 명',
  'code_dc' VARCHAR(45) NULL COMMENT '코드 설명',
  'use_yn' VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  'del_yn' VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부 YN',
  'reg_dt' DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  'reg_user_id' VARCHAR(45) NULL COMMENT '등록 사용자 아이디',
  'upd_dt' DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  'upd_user_id' VARCHAR(45) NULL COMMENT '수정 사용자 아이디',
  PRIMARY KEY ('code_group_id', 'code_id'),
  CONSTRAINT 'fk_tbl_comm_code_tbl_comm_code_group1'
    FOREIGN KEY ('code_group_id')
    REFERENCES 'study'.'tbl_comm_code_group' ('code_group_id')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;

-- 뉴스 RSS URL
CREATE TABLE IF NOT EXISTS 'study'.'tbl_news_rss_url' (
  'rss_url_seq' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'RSS URL 일련번호',
  'comp_cd' VARCHAR(45) NULL COMMENT '언론사 코드 - G1',
  'cl_cd' VARCHAR(45) NULL COMMENT '뉴스 분야 코드 - G2',
  'rss_url' VARCHAR(300) NOT NULL COMMENT 'RSS URL',
  'use_yn' VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부 YN',
  'last_build_date' VARCHAR(100) NULL COMMENT '마지막 작성 일시',
  'reg_dt' DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  'reg_user_id' VARCHAR(45) NULL COMMENT '등록자 아이디',
  'upd_dt' DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  'upd_user_id' VARCHAR(45) NULL COMMENT '수정자 아이디',
  PRIMARY KEY ('rss_url_seq'),
  UNIQUE INDEX 'url_UNIQUE' ('rss_url' ASC))
ENGINE = InnoDB
;

-- 뉴스
CREATE TABLE IF NOT EXISTS 'study'.'tbl_news' (
  'news_seq' INT NOT NULL AUTO_INCREMENT COMMENT '뉴스 고유번호',
  'comp_cd' VARCHAR(45) NULL COMMENT '언론사 코드 - G1',
  'cl_cd' VARCHAR(45) NULL COMMENT '뉴스 분야 코드 - G2',
  'news_title' VARCHAR(200) NULL COMMENT '뉴스 제목',
  'news_content' BLOB NULL COMMENT '뉴스 내용',
  'news_url' VARCHAR(45) NULL COMMENT '뉴스 URL',
  'pub_dt' VARCHAR(45) NULL COMMENT '발행 일시(publication date)',
  'reg_dt' DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  'upd_dt' DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  PRIMARY KEY ('news_seq'))
ENGINE = InnoDB
;

-- 사용자
CREATE TABLE IF NOT EXISTS 'study'.'tb_user' (
  'user_seq' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '사용자 일련번호',
  'parent_site_type' VARCHAR(10) NOT NULL COMMENT '부모 사이트 종류',
  'id' VARCHAR(20) NOT NULL COMMENT 'ID',
  'name' VARCHAR(20) NOT NULL COMMENT '이름',
  'email' VARCHAR(50) NOT NULL COMMENT '이메일',
  'image_url' VARCHAR(250) NULL COMMENT '이미지 URL',
  'use_yn' VARCHAR(45) NOT NULL DEFAULT 'Y' COMMENT '사용여부YN',
  'login_fail_cnt' INT NOT NULL DEFAULT 0 COMMENT '로그인 실패 횟수',
  'reg_dt' DATE NOT NULL DEFAULT now() COMMENT '등록일시',
  'reg_user_seq' BIGINT(20) NULL COMMENT '등록자 일련번호',
  'upd_dt' DATE NOT NULL DEFAULT now() COMMENT '수정일시',
  'upd_user_seq' BIGINT(20) NULL COMMENT '수정자 일련번호',
  PRIMARY KEY ('user_seq'),
  INDEX 'tb_user' ('parent_site_type' ASC, 'id' ASC),
  UNIQUE INDEX 'email_UNIQUE' ('email' ASC) )
ENGINE = InnoDB
;

-- 로그인 이력
CREATE TABLE IF NOT EXISTS 'study'.'tb_login_hist' (
  'login_hist_seq' BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '로그인 이력 일련번호',
  'user_seq' BIGINT(20) NOT NULL COMMENT '사용자 일련번호',
  'ip' VARCHAR(45) NULL COMMENT 'ip',
  'login_succ_yn' VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '로그인 성공 여부',
  'login_dt' DATE NOT NULL DEFAULT now() COMMENT '로그인 일시',
  INDEX 'fk_login_hist_tb_user1_idx' ('user_seq' ASC),
  PRIMARY KEY ('login_hist_seq'),
  CONSTRAINT 'fk_login_hist_tb_user1'
    FOREIGN KEY ('user_seq')
    REFERENCES 'study'.'tb_user' ('user_seq')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;

-- 로또
CREATE TABLE IF NOT EXISTS 'study'.'tb_lotto' (
  'turn_no' INT NOT NULL,
  'turn_day' VARCHAR(8) NOT NULL,
  'no1' INT NOT NULL,
  'no2' INT NOT NULL,
  'no3' INT NOT NULL,
  'no4' INT NOT NULL,
  'no5' INT NOT NULL,
  'no6' INT NOT NULL,
  'no7' INT NOT NULL,
  PRIMARY KEY ('turn_no'),
  UNIQUE INDEX 'turn_no_UNIQUE' ('turn_no' ASC))
ENGINE = InnoDB


/* ****************************************************
 * 공통코드
 * ***************************************************/

-- 뉴스 언론사
INSERT INTO tbl_comm_code_group(code_group_id, code_group_nm, code_group_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G1', '뉴스 언론사', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G1', 'G1C1', '한겨레', null, 'Y', 'N', NOW(), null, NOW(), null);

-- 뉴스 분야
INSERT INTO tbl_comm_code_group(code_group_id, code_group_nm, code_group_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G2', '뉴스 분야', null, 'Y', 'N', NOW(), null, NOW(), null);

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


-- 사용자 계정의 사이트
INSERT INTO tbl_comm_code_group(code_group_id, code_group_nm, code_group_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G3', '사용자 계정 사이트', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G3', 'G3C1', '구글', null, 'Y', 'N', NOW(), null, NOW(), null);

INSERT INTO tbl_comm_code(code_group_id, code_id, code_nm, code_dc, use_yn, del_yn, reg_dt, reg_user_id, upd_dt, upd_user_id)
VALUES('G3', 'G3C2', '네이버', null, 'Y', 'N', NOW(), null, NOW(), null);


-- 로그인 시도
	로그인 시도 일련번호
	사용자 일련번호
	접근 아이피
	로그인 성공실패 여부
	일시


-- 사용자
	로그인 실패 건수


-- 번호
	회차
	당첨일자
	번호1~6
	보너스
	등록일시


TODO
	용어사진
	과거 회차 번호 등록

