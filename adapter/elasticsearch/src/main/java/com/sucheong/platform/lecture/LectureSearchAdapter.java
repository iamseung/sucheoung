package com.sucheong.platform.lecture;

import com.sucheong.platform.lecture.model.Lecture;
import com.sucheong.platform.port.LectureSearchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LectureSearchAdapter implements LectureSearchPort {

    private final LectureSearchRepository lectureSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public void indexLecture(Lecture lecture) {
        lectureSearchRepository.save(toDocument(lecture));
    }

    @Override
    public void deleteLecture(Long lectureId) {
            lectureSearchRepository.deleteById(lectureId);
    }

    @Override
    public List<Long> searchLectureIdsByKeyword(String keyword, int pageNumber, int pageSize) {
        if(keyword == null || keyword.isBlank() || pageNumber < 0 || pageSize < 0) {
            return List.of();
        }

        Query query = buildQuery(keyword, pageNumber, pageSize);
        SearchHits<LectureDocument> search = elasticsearchOperations.search(query, LectureDocument.class);

        return search.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(LectureDocument::getId)
                .toList();
    }

    private Query buildQuery(String keyword, int pageNumber, int pageSize) {
        Criteria criteria = new Criteria("title").matches(keyword)
                .or(new Criteria("content").matches(keyword))
                .or(new Criteria("id").is(keyword));
        return new CriteriaQuery(criteria)
                .setPageable(PageRequest.of(pageNumber, pageSize));
    }

    private LectureDocument toDocument(Lecture lecture) {
        return new LectureDocument(
                lecture.getId(),
                lecture.getTitle(),
                lecture.getContent(),
                lecture.getCapacity(),
                LocalDateTime.now()
        );
    }
}
