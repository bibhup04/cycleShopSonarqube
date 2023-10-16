package com.talentsprint.cycleshop.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentsprint.cycleshop.business.LoginBody;
import com.talentsprint.cycleshop.business.Token;
import com.talentsprint.cycleshop.service.DomainUserService;
import com.talentsprint.cycleshop.service.RegistrationForm;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class APIAuthController {

  @Autowired
  JwtEncoder jwtEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  private DomainUserService domainUserService;

  String userRole = "admin";

  @PostMapping("/token")
  public Token token(@RequestBody LoginBody loginBody) {

    Instant now = Instant.now();

    System.out.println("inside api/auth/token");

    long expiry = 3600L;

    System.out.println(loginBody.getUsername());
    System.out.println(loginBody.getPassword());

    var username = loginBody.getUsername();
    var password = loginBody.getPassword();

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));

    System.out.println("authentication: " + authentication);

    String scope = authentication.getAuthorities().stream()

        .map(GrantedAuthority::getAuthority)

        .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()

        .issuer("self")

        .issuedAt(now)

        .expiresAt(now.plusSeconds(expiry))

        .subject(authentication.getName())

        .claim("scope", scope)

        .claim("userRole", userRole)

        .build();

    System.out.println(this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());

    var token = new Token();
    token.setToken(this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    return token;
  }


  @PostMapping("/outsideSource")
    public Token outsideUser(@RequestBody LoginBody loginBody) {
      var token = new Token();
        try {
            System.out.println("third party user name in outsideUser class: " + loginBody.getUsername());
            System.out.println("third party password in outsideUser class: " + loginBody.getPassword());


            if (domainUserService.getByName(loginBody.getUsername()) != null) {
                token = token(loginBody);
            }
            System.out.println(domainUserService.save(loginBody.getUsername(), loginBody.getPassword(),
                    "USER"));

            token = token(loginBody);
            return token;
        } catch (Exception e) {
           
        }
        return token;
    }

}