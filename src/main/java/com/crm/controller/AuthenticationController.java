package com.crm.controller;

import com.crm.domain.User;
import com.crm.dto.LoginUserDto;
import com.crm.security.AuthorizationToken;
import com.crm.security.JwtTokenUtil;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// controller to handle authentication requests
@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    // invalidate the token
    @RequestMapping(value = "/invalidate", method = RequestMethod.GET)
    public String invalidate(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "logged out";
    }
    // check if the token is valid
    @RequestMapping(value = "/check", method = RequestMethod.GET, produces="text/plain")
    public String check(){
        return "valid";
    }
    // generate the token
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUserDto loginUserDto) throws AuthenticationException {
        // get user password from front-end
        String password = new String(Base64.decodeBase64(loginUserDto.getPassword()));
        User user = userService.getUser(loginUserDto.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean is;
        // compare use password in database
        is = encoder.matches(password, user.getPassword());
        // authenticate the user
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user2 = userService.getUser(loginUserDto.getUsername());
        final String token = jwtTokenUtil.generateToken(user2);
        // create the token
        final AuthorizationToken authorizationToken = new AuthorizationToken(token);
        return ResponseEntityHelper.jsonTokenResponse(authorizationToken.getToken());
    }
}
