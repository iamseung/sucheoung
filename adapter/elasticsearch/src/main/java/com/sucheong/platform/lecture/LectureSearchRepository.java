package com.sucheong.platform.lecture;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LectureSearchRepository extends ElasticsearchRepository<LectureDocument, Long> {
}
