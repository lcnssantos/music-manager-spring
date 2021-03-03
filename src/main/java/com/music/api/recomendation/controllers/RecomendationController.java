package com.music.api.recomendation.controllers;

import com.music.api.music.responses.MusicResponse;
import com.music.api.recomendation.responses.RecomendationResponse;
import com.music.api.recomendation.services.RecomendationService;
import com.music.api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recomendation")
public class RecomendationController {
    @Autowired
    private RecomendationService recomendationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecomendationResponse>> getRecomendation(HttpServletRequest request) {
        try {
            User user = (User) request.getAttribute("user");

            List<RecomendationResponse> response = this.recomendationService.get(user).stream()
                    .map(music -> new RecomendationResponse(music))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
