package com.sucheong.platform;

import com.sucheong.platform.model.MetadataResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class MetadataClient {

    private final WebClient webClient;

    @Value("${OD_CLOUD_API_KEY}")
    private String odCloudApiKey;

    public String getLectures() {
        MetadataResponse response = webClient.get()
                .uri("/15067374/v1/uddi:d7e664dc-743b-4147-b712-f6056a92ac0e?serviceKey="+odCloudApiKey)
                .retrieve()
                .bodyToMono(MetadataResponse.class)
                .block();

        // TODO 도메인 나오면 변경
        assert response != null;
        log.info(Arrays.toString(response.getData()));

        return Arrays.toString(response.getData());
    }

    @Data
    public static class Lecture {
        private String title;
        private String professorName;
    }

}
