// package com.talentsprint.cycleshop.util;

// import com.talentsprint.cycleshop.vo.UserAuth;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
// import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Component;

// import java.util.Map;

// @Component
// public class AuthenticationUtil {

//     @Autowired
//     OAuth2AuthorizedClientService clientService;

//     public UserAuth getAuthToken() {
//         Authentication authentication =
//                 SecurityContextHolder
//                         .getContext()
//                         .getAuthentication();

//         String githubId = null;
//         String userEmail = null;
//         String name = null;
//         if (authentication instanceof OAuth2AuthenticationToken) {
//             OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
//             OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
//             Map<String, Object> attributes = oAuth2User.getAttributes();
//             System.out.println(attributes);
//             System.out.println("------------------------");
//             githubId = attributes.get("login").toString();
//             userEmail = attributes.get("email").toString();
//             name = attributes.get("name").toString();
//         }

//         OAuth2AuthenticationToken oauthToken =
//                 (OAuth2AuthenticationToken) authentication;

//         OAuth2AuthorizedClient client =
//                 clientService.loadAuthorizedClient(
//                         oauthToken.getAuthorizedClientRegistrationId(),
//                         oauthToken.getName());

//         UserAuth userAuth = new UserAuth();
//         userAuth.setAuthToken(client.getAccessToken().getTokenValue());
//         userAuth.setPrincipalName(githubId);
//         userAuth.setEmail(userEmail);
//         userAuth.setDisplayName(name);
//         return userAuth;
//     }
// }

