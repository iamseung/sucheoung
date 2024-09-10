package com.sucheong.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class MetadataResponse {

    private int page;
    private int perPage;
    private int totalCount;
    private int currentCount;
    private int matchCount;
    private LectureData[] data;

    @Data
    @Getter
    public static class LectureData {
        @JsonProperty("강의명")
        private String title;

        @JsonProperty("교수")
        private String professorName;

        @JsonProperty("대분류")
        private String majorCategory;

        @JsonProperty("중분류")
        private String middleCategory;

        @JsonProperty("소분류")
        private String subCategory;

        @JsonProperty("대학")
        private String university;

        @JsonProperty("학년학기")
        private String term;

        @JsonProperty("바로가기 URL")
        private String url;
    }
}
