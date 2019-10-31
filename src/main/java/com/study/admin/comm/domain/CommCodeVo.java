package com.study.admin.comm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommCodeVo {

	/** 코드 그룹 ID */
	private String codeGroupId;
	
	/** 코드 ID */
    private String codeId;
    
    /** 코드 명 */
    private String codeNm;
    
    /** 코드 설명 */
    private String codeDc;
    
    /** 사용여부 YN */
    private String useYn;
    
    /** 삭제여부 YN */
    private String delYn;
    
    /** 등록일시 */
    private String regDt;
    
    /** 등록자 ID */
    private String regUserId;
    
    /** 수정일시 */
    private String updDt;
    
    /** 수정자 ID */
    private String updUserId;
	
}
