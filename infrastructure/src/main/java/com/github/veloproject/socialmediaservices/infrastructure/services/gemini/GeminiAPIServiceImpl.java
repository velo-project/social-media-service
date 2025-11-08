package com.github.veloproject.socialmediaservices.infrastructure.services.gemini;

import com.github.veloproject.socialmediaservices.application.abstractions.IGeminiAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GeminiAPIServiceImpl implements IGeminiAPIService {
    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.embedding.model}")
    private String embeddingModel;

    private final WebClient webClient;

    public GeminiAPIServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @SuppressWarnings("unchecked")
    public float[] generateEmbeddings(String text) {
        Map<String, Object> body = Map.of(
                "model", embeddingModel,
                "content", Map.of("parts", List.of(Map.of("text", text))),
                "outputDimensionality", 1536,
                "taskType", "SEMANTIC_SIMILARITY"
        );

        Map<String, Object> response = webClient.post()
                .uri("/models/gemini-embedding-exp-03-07:embedContent?key=" + apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        Map<String, Object> embedding = (Map<String, Object>) response.get("embedding");
        List<Double> valuesList = (List<Double>) embedding.get("values");

        float[] floatArray = new float[valuesList.size()];
        for (int i = 0; i < valuesList.size(); i++) {
            floatArray[i] = valuesList.get(i).floatValue();
        }

        return floatArray;
    }
}
