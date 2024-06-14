package com.genie.cricket.service;

import com.genie.cricket.dto.ScoreDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CricketScoreService  {

    @Autowired
    RestTemplate restTemplate;
    public ScoreDto liveScore() {
        ScoreDto scoreDto = new ScoreDto();
        String url = "https://www.google.com/search?q=tomorrow+match+england+and+oman";

        try {
            // Fetch the HTML content from the URL
            Document doc = Jsoup.connect(url).get();

            // Find the score container element
            Element scoreContainer = doc.selectFirst(".imso_mh__scr-sep .imspo_mh_cricket__first-score");

            if (scoreContainer != null) {
                // Extract the score major and score minor elements
                Element scoreMajorElement = scoreContainer.selectFirst(".imspo_mh_cricket__score-major");
                Element scoreMinorElement = scoreContainer.selectFirst(".imspo_mh_cricket__score-minor");

                // Get the text content of score major and score minor
                String scoreMajor = scoreMajorElement != null ? scoreMajorElement.text() : "";
                String scoreMinor = scoreMinorElement != null ? scoreMinorElement.text() : "";

                // Split scoreMajor into runs and wickets
                String[] scoreParts = scoreMajor.split("/");
                if (scoreParts.length == 2) {
                    scoreDto.setRun(scoreParts[0].trim());
                    scoreDto.setOut(scoreParts[1].trim());
                } else {
                    scoreDto.setRun(scoreMajor.trim());
                    scoreDto.setOut("0"); // Handle if wickets info is not available
                }
                scoreDto.setOver(scoreMinor.trim());
            } else {
                System.out.println("Score container not found");
                // Handle this case, e.g., set an error message in scoreDto
            }

        } catch (IOException e) {
            e.printStackTrace(); // Consider logging this exception properly
            // Handle this case, e.g., set an error message in scoreDto
        }

        return scoreDto;
    }
}
