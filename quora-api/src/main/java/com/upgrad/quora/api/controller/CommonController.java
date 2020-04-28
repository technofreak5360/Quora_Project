package com.upgrad.quora.api.controller;


import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class CommonController {

    @Autowired
    UserAdminService userAdminService;

    @RequestMapping(method = RequestMethod.GET, path = "/userprofile/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDetailsResponse> getUser(@PathVariable("userId") final String userUuid, @RequestHeader("authorization") final String authorization) throws UserNotFoundException, AuthorizationFailedException {
        final UserEntity userEntity = userAdminService.getUser(userUuid, authorization, false);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse()
                .userName(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .emailAddress(userEntity.getEmail())
                .contactNumber(userEntity.getContactNumber())
                .country(userEntity.getCountry())
                .aboutMe(userEntity.getAboutMe())
                .dob(userEntity.getDob());
//                .status(UserStatusType.valueOf(UserStatus.getEnum(userEntity.getStatus()).name()));
        return new ResponseEntity<UserDetailsResponse>(userDetailsResponse, HttpStatus.OK);
    }

}
