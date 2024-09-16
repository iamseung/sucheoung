package com.sucheong.platform.enrollment;

import com.sucheong.platform.port.EnrollmentRequestHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class EnrollmentRequestHistoryAdapter implements EnrollmentRequestHistoryPort {

    // TODO 키 설정 논의
    private static final String USER_REQUEST_HISTORY_KEY_PREFIX = "enrollment:user_request:v1:%d:%d";
    private static final String REQUEST_COUNT_HISTORY_KEY_PREFIX = "enrollment:request_count:v1:%d";
    private static final Long EXPIRE_SECONDS = 60 * 60 * 24 * 7L; // 7days

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean setHistoryIfNotExists(Long lectureId, Long memberId) {
        return redisTemplate.opsForValue().setIfAbsent(
                this.generateUserRequestHistoryCacheKey(lectureId, memberId),
                "1",
                Duration.ofSeconds(EXPIRE_SECONDS)
        );
    }

    @Override
    public Long getRequestSequence(Long lectureId) {
        String key = this.generateRequestCountHistoryCacheKey(lectureId);
        Long requestSequence = redisTemplate.opsForValue().increment(key); // 증가 시 synchronized

        // key 처음 생성되었다면
        if(requestSequence != null && requestSequence == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(EXPIRE_SECONDS));
        }

        return requestSequence;
    }

    private String generateUserRequestHistoryCacheKey(Long lectureId, Long memberId) {
        return USER_REQUEST_HISTORY_KEY_PREFIX.formatted(lectureId, memberId);
    }

    private String generateRequestCountHistoryCacheKey(Long lectureId) {
        return REQUEST_COUNT_HISTORY_KEY_PREFIX.formatted(lectureId);
    }
}
