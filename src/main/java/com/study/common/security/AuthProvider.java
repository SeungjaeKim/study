package com.study.common.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.study.common.CommCode;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {
     
//    @Autowired
//    UserService userService; 
	 
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
				Payload payload = idToken.getPayload();

				String userId = payload.getSubject();
				System.out.println("User ID: " + userId);

				// Get profile information from payload
				String email = payload.getEmail();

				boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				String name = (String) payload.get("name");
				String pictureUrl = (String) payload.get("picture");
				String locale = (String) payload.get("locale");
				String familyName = (String) payload.get("family_name");
				String givenName = (String) payload.get("given_name");

				log.info("email=" +  email);
				log.info("name=" +  name);
				
				
				if (loginEmail.equals(email)) {
					
					log.info("로그인성공@@@@");
					
				}
				
				
				// Use or store profile information
		        LoginUserVo loginUserVo = new LoginUserVo();
		        loginUserVo.setEmail("12345@gmail.com");
		        
		        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		        roleList.add(new SimpleGrantedAuthority("ROLE_USER"));
		 
		        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginEmail, loginEmail, roleList);
		        authenticationToken.setDetails(loginUserVo);
		        
		        return authenticationToken;

			} else {
				throw new UsernameNotFoundException("User Not Found");
			}
		} catch (GeneralSecurityException | IOException e) {
			throw new UsernameNotFoundException(e.getMessage(), e.fillInStackTrace());
		}
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
 
}
