function onClickHref(event) {
    let url = event.currentTarget.getAttribute("href");
    location.href = url;
}

// <td><a href="url"> 1 </a></td>
function createPageTd(no, label, url) {
    let td = document.createElement("td");
    let a = document.createElement("a");

    a.appendChild(document.createTextNode(label));
    a.setAttribute("href", url.replace(/pg=[0-9]+/, "pg=" + no));
    td.appendChild(a);
    return td;
}

function pagination() {
    let div = document.getElementsByClassName("pagination")[0];

    // 전체 레코드 수
    let recordCount = parseInt(div.getAttribute("data-record-count"));
    let pageSize = parseInt(div.getAttribute("data-page-size"));
    let currentPage = parseInt(div.getAttribute("data-current-page"));

    // 전체 페이지 수
    let pageCount = Math.ceil(recordCount / pageSize);

    // 현재 페이지
    if (currentPage > pageCount) currentPage = pageCount;
    let url = location.href;

    if (url.indexOf("pg=") < 0)
        url = url + (url.indexOf("?") < 0 ? "?pg=1" : "&pg=1");

    let table = document.createElement("table");
    let tr = document.createElement("tr");

    // 시작되는 번호 구하기
    let baseNo = Math.floor((currentPage - 1) / 10) * 10;

    if (baseNo > 0) tr.appendChild(createPageTd(baseNo, "<", url));
    for (let i = 1; i <= 10; ++i) {
        let no = baseNo + i;
        if (no > pageCount) break;
        let td = createPageTd(no, String(no), url)
        if (no == currentPage) td.classList.add("active");
        tr.appendChild(td);
    }
    let no = baseNo + 11;
    if (no <= pageCount) tr.appendChild(createPageTd(no, ">", url));
    table.appendChild(tr);
    div.appendChild(table);
}
