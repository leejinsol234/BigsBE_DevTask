package bigs.task.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    public void weatherMethod(){
        String url = "";
        ResponseEntity response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
    }
}
