package com.mybatis4.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class Pagination {
    int pg = 1;        // 현재 페이지 번호
    int sz = 15;       // 페이지 당 레코드 수
    int recordCount;   // 전체 레코드 수
    String url;        // 목록 페이지 url

    public int getFirstRecordIndex() {
        return (pg - 1) * sz;
    }

    public String getQueryString() {
        return String.format("pg=%d&sz=%d", pg, sz);
    }

    public List<Page> getPages() {
        List<Page> pages = new ArrayList<Page>();
        int pageCount = (int)Math.ceil((float)recordCount / sz);
        if (pg > pageCount) pg = pageCount;
        if (url.indexOf("pg=") < 0)
            url = url + (url.indexOf("?") < 0 ? "?" : "&") + "pg=1";
        int baseNo = (pg - 1) / 10 * 10;
        if (baseNo > 0)
            pages.add(new Page("<", false, url.replaceAll("pg=[0-9]+", "pg=" + baseNo)));
        for (int i = 1; i <= 10; ++i) {
            int no = baseNo + i;
            if (no > pageCount) break;
            pages.add(new Page(String.valueOf(no), no == pg,
                    url.replaceAll("pg=[0-9]+", "pg=" + no)));
        }
        int no = baseNo + 11;
        if (no <= pageCount)
            pages.add(new Page(">", false, url.replaceAll("pg=[0-9]+", "pg=" + no)));
        return pages;
    }

}
@Data
@AllArgsConstructor
class Page {
    String label;
    boolean active;
    String url;
}


