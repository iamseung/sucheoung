package com.sucheong.platform.lecture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sucheong.platform.CustomObjectMapper;
import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.LectureCachePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LectureCacheAdapter implements LectureCachePort {

    private static final String LECTURE_KEY_PREFIX = "lecture:v1:";
    private static final Long EXPIRE_SECONDS = 60 * 2L;

    private final RedisTemplate<String, String> redisTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void set(Lecture lecture) {
        String jsonString;

        try {
            jsonString = objectMapper.writeValueAsString(lecture);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        redisTemplate.opsForValue().set(
                this.generateCacheKey(lecture.getId()),
                jsonString,
                Duration.ofSeconds(EXPIRE_SECONDS)
        );
    }

    @Override
    public Lecture get(Long lectureId) {
        String jsonString = redisTemplate.opsForValue().get(this.generateCacheKey(lectureId));
        if(jsonString == null) return null;

        try {
            return objectMapper.readValue(jsonString, Lecture.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lecture> multiGet(List<Long> lectureIds) {

        List<String> jsonStrings = redisTemplate.opsForValue().multiGet(lectureIds.stream().map(this::generateCacheKey).toList());
        if(jsonStrings == null) return List.of();

        return jsonStrings.stream().filter(Objects::nonNull).map(jsonString -> {
            try {
                return objectMapper.readValue(jsonString, Lecture.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    private String generateCacheKey(Long lectureId) {
        return LECTURE_KEY_PREFIX + lectureId;
    }
}
