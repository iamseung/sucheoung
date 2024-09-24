package com.sucheong.platform.enrollment;

import com.sucheong.platform.port.EnrollmentRequestHistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EnrollmentRequestHistoryAdapter implements EnrollmentRequestHistoryPort {

    private static final String USER_REQUEST_HISTORY_KEY_PREFIX = "enrollment:user_request:v1:%d:%d";
    private static final String REQUEST_COUNT_HISTORY_KEY_PREFIX = "enrollment:request_count:v1:%d";
    private static final String USER_ENROLLED_LECTURES_SEARCH_KEY = "enrollment:user_request:v1:%d:*";
    private static final String REQUEST_USERS_SEARCH_KEY = "enrollment:user_request:v1:*:%d";
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

    @Override
    public int getRequestSequentialNumber(Long lectureId) {
        String key = this.generateRequestCountHistoryCacheKey(lectureId);
        Integer requestSequentialNumber = Integer.parseInt(redisTemplate.opsForValue().get(key));
        return requestSequentialNumber != null ? requestSequentialNumber : 0;
    }

    @Override
    public List<Long> getEnrolledLecturesByMemberId(Long memberId) {
        String searchKey = this.getUserEnrolledLecturesSearchKey(memberId);
        List<Long> lectureIds = redisTemplate
                .scan(ScanOptions.scanOptions()
                        .match(searchKey)
                        .build())
                .stream().map(key -> Long.valueOf(key.split(":")[4])).toList();

        if(lectureIds == null || lectureIds.isEmpty()) {
            return List.of();
        }

        return lectureIds;
    }

    @Override
    public List<Long> getEnrolledUsersByLectureId(Long lectureId) {
        String searchKey = this.getRequestUsersSearchKey(lectureId);
        List<Long> memberIds = redisTemplate
                .scan(ScanOptions.scanOptions()
                        .match(searchKey)
                        .build())
                .stream().map(key -> Long.valueOf(key.split(":")[3])).toList();

        if(memberIds == null || memberIds.isEmpty()) {
            return List.of();
        }

        return memberIds;
    }

    private String generateUserRequestHistoryCacheKey(Long memberId, Long lectureId) {
        return USER_REQUEST_HISTORY_KEY_PREFIX.formatted(memberId, lectureId);
    }

    private String generateRequestCountHistoryCacheKey(Long lectureId) {
        return REQUEST_COUNT_HISTORY_KEY_PREFIX.formatted(lectureId);
    }

    private String getUserEnrolledLecturesSearchKey(Long memberId) {
        return USER_ENROLLED_LECTURES_SEARCH_KEY.formatted(memberId);
    }

    private String getRequestUsersSearchKey(Long lectureId) {
        return REQUEST_USERS_SEARCH_KEY.formatted(lectureId);
    }
}
