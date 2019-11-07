package com.study.common.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.study.admin.user.domain.LoginUserVo;
import com.study.admin.user.service.UserAdmService;
import com.study.common.constant.CommCode;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserAdmService userAdmService;

	/**
	 * 구글 API 클라이언트 ID
	 */
	@Value("${gapi.client.id}")
	private String gapiClientId;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginEmail = authentication.getName();
        String loginToken = authentication.getCredentials().toString();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
        	    .setAudience(Collections.singletonList(gapiClientId))
        	    .build();

        try {
			GoogleIdToken idToken = verifier.verify(loginToken);

			if (idToken != null) {
				throw new UsernameNotFoundException("User Not Found");
			}

			Payload payload = idToken.getPayload();

			String userId = payload.getSubject();
			String email  = payload.getEmail();
			String name   = (String) payload.get("name");

			//TODO 정확한 사용법을 확인하자
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());

//			String pictureUrl = (String) payload.get("picture");
//			String locale     = (String) payload.get("locale");
//			String familyName = (String) payload.get("family_name");
//			String givenName  = (String) payload.get("given_name");

			//로그인화면에서 전달된 이메일과 인증토큰을 통해 조회된 이메일을 비교하여
			//동일한 경우에만 로그인 성공으로 판단한다
			if (!loginEmail.equals(email)) {
				throw new UsernameNotFoundException("User Not Found");
			}

			//기존 사용자 조회
			LoginUserVo loginUserVo = new LoginUserVo();
			loginUserVo.setId(userId);
			loginUserVo.setEmail(email);
			loginUserVo = (LoginUserVo) userAdmService.selectUser(loginUserVo);

			//신규가입
			if (loginUserVo == null) {
				//신규 사용자 등록
				loginUserVo.setParentSiteTy(CommCode.ParentSiteTy.GOOGLE.getCd());  //부모 사이트 종류 - 구글
				loginUserVo.setId(userId);
				loginUserVo.setEmail(email);
				loginUserVo.setName(name);
				userAdmService.insertUser(loginUserVo);
			}
			//ID 일치
			else if (userId.equals(loginUserVo.getId())) {

				//로그인 일시 정보 갱신

			}
			//ID 불일치
			else {
				throw new UsernameNotFoundException("User Not Found");
			}

	        //사용자 권한 정보 설정
	        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
	        roleList.add(new SimpleGrantedAuthority("ROLE_USER"));

	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginEmail, loginEmail, roleList);
	        authenticationToken.setDetails(loginUserVo);

	        return authenticationToken;

		} catch (GeneralSecurityException | IOException e) {
			throw new UsernameNotFoundException(e.getMessage(), e.fillInStackTrace());
		}
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
