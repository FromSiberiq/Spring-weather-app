package com.example;

import com.example.dto.WeatherResponse;
import jakarta.servlet.http.HttpServletRequest;
import retrofit2.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {

    @GetMapping("/api/v1/weather-in-city")
    public WeatherResponse weatherInCity(@RequestParam(required = false) String query, HttpServletRequest request) {
        String q = query;
        if (q == null) {
            q = request.getRemoteAddr();
        }

        try {
            Response <WeatherResponse> response = Weather.instance().current(Weather.WEATHER_API_KEY, q).execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
