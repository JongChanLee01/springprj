package com.board.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api") // 이렇게 api라고 표기해두는것이 좋음
public class TestRestController {
    @GetMapping("hello3")
    public HashMap<String, String> hello3(){
        HashMap<String, String> map = new HashMap<>(){
            {
                put("이름","홍길동");
                put("나이","30");
                put("국적","서울");
            }
        };
        return map;
    }

    @GetMapping("hello4")
    public HashMap<String, String> hello4(){
        HashMap<String, String> map = new HashMap<>();
            map.put("이름","원빈");
            map.put("나이","40");
            map.put("국적","시흥");
        return map;
    }
}
