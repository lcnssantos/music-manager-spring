package com.music.api.avaliation.controllers;

import com.music.api.avaliation.requests.CreateAvaliationRequest;
import com.music.api.avaliation.responses.AvaliationResponse;
import com.music.api.avaliation.services.AvaliationService;
import com.music.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/avaliation")
public class AvaliationController {
    @Autowired
    private AvaliationService avaliationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvaliationResponse> createAvaliation(
            @Valid @RequestBody CreateAvaliationRequest createRequest,
            HttpServletRequest request
    ) {
        try {
            User user = (User) request.getAttribute("user");
            this.avaliationService.create(user, createRequest.score, createRequest.musicId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
