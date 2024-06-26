package com.city.model;

import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
public class Pagination {
    int pg = 1; // 현재 페이지 번호
    int sz = 5; // 페이지 당 레코드 수
    int recordCount;   // 전체 레코드 수
    int od = 0;        // 정렬 순서
    String st = "";    // 검색 문자열

    public int getFirstRecordIndex() {
        return (pg - 1) * sz;
    }

    public String getQueryString() {
        try{
            String encoded = URLEncoder.encode(st, "UTF-8");
            return String.format("pg=%d&sz=%d&od=%d&st=%s", pg, sz, od, encoded);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("pg=%d&sz=%d&od=%d&st=%s", pg, sz, od, st);
    }
}
