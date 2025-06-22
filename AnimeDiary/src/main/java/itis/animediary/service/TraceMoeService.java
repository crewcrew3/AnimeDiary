package itis.animediary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itis.animediary.service.dto.tracemoe.TraceMoeResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TraceMoeService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TraceMoeResponse searchAnimeByImageUrl(String imageUrl) throws IOException {
        String apiUrl = "https://api.trace.moe/search?anilistInfo&url=" + imageUrl;

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Ошибка API: " + response.code());
            }
            String responseBody = response.body().string();

            try {
                TraceMoeResponse moeResponse = objectMapper.readValue(responseBody, TraceMoeResponse.class);
                return moeResponse;
            } catch (JsonProcessingException e) {
                throw e;
            }
        }
    }
}
