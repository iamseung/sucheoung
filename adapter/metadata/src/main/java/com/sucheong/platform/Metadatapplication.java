package com.sucheong.platform;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class Metadatapplication {
    //TODO 도메인 나오면 삭제

    private final MetadataAdapter a;

    public static void main(String[] args) {
        SpringApplication.run(Metadatapplication.class, args);
    }

//    @GetMapping("/123")
//    public String test() {
//        return a.getLectures();
//    }

}
