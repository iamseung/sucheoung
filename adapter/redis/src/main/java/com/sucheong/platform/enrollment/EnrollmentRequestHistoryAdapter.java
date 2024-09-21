package com.sucheong.platform.enrollment;

import com.sucheong.platform.port.EnrollmentRequestHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class EnrollmentRequestHistoryAdapter implements EnrollmentRequestHistoryPort {

    private static final String USER_REQUEST_HISTORY_KEY_PREFIX = "enrollment:user_request:v1:%d:%d";
    private static final String REQUEST_COUNT_HISTORY_KEY_PREFIX = "enrollment:request_count:v1:%d";
    private static final Long EXPIRE_SECONDS = 60 * 60 * 24 * 7L; // 7days

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean setHistoryIfNotExists(Long memberId, Long lectureId) {
        return redisTemplate.opsForValue().setIfAbsent(
                this.generateUserRequestHistoryCacheKey(memberId, lectureId),
                "1",
                Duration.ofSeconds(EXPIRE_SECONDS)
        );
    }

    @Override
    public boolean hasRemainingQuantity(Long lectureId, int capacity) {
        String key = this.generateRequestCountHistoryCacheKey(lectureId);
        Long requestSequence = redisTemplate.opsForValue().increment(key);

        // key 처음 생성되었다면
        if(requestSequence != null && requestSequence == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(EXPIRE_SECONDS));
        }

        if(requestSequence > capacity) {
            redisTemplate.opsForValue().decrement(key);
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteRequest(Long memberId, Long lectureId) {
        redisTemplate.opsForValue().decrement(generateRequestCountHistoryCacheKey(lectureId));

        return redisTemplate.delete(generateUserRequestHistoryCacheKey(memberId, lectureId));
    }


    private String generateUserRequestHistoryCacheKey(Long memberId, Long lectureId) {
        return USER_REQUEST_HISTORY_KEY_PREFIX.formatted(memberId, lectureId);
    }

    private String generateRequestCountHistoryCacheKey(Long lectureId) {
        return REQUEST_COUNT_HISTORY_KEY_PREFIX.formatted(lectureId);
    }
}
