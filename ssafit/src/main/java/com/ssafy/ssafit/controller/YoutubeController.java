package com.ssafy.ssafit.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
//@CrossOrigin("*") // 우선 작동하는지 보기위해...
@RequestMapping("/youtube")
public class YoutubeController {
	
	// RestTemplate은 Spring에서 HTTP 요청을 보내기 위한 간단한 방법을 제공
    private RestTemplate restTemplate = new RestTemplate();
    
    @GetMapping("")
    public  Map<String, Object> getYoutubeVideos(@RequestParam("key") String key, @RequestParam("q") String keyword) {
        
    	String url = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=%s&type=video&key=%s", keyword, key);
        
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }
}