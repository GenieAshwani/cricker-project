package com.genie.cricket.controller;


import com.genie.cricket.dto.ScoreDto;
import com.genie.cricket.service.CricketScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("cricket")
public class CricketController {

    @Autowired
    CricketScoreService cricketScoreService;



    @GetMapping("/live-score")
    public ScoreDto getPlayerDetails() {
       return   cricketScoreService.liveScore();
    }
}
