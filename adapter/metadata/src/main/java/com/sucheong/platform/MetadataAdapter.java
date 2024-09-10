package com.sucheong.platform;

import com.sucheong.platform.model.MetadataResponse;
import com.sucheong.platform.port.MetadataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MetadataAdapter implements MetadataPort {

    private final MetadataClient metadataClient;

    // TODO 도메인 나오면 변경
    @Override
    public String getLectures() {
        return metadataClient.getLectures();
    }
}
