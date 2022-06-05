export const httpGet = (url) => {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open('GET', url, false);
    xmlHttp.send(null);

    return xmlHttp;
}
export const httpPost = (url, body) => {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open('POST', url, false);
    xmlHttp.setRequestHeader('Content-type', 'application/json')
    xmlHttp.send(body);

    return xmlHttp;
}
export const httpPut = (url, body) => {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open('PUT', url, false);
    xmlHttp.setRequestHeader('Content-type', 'application/json')
    xmlHttp.send(body);

    return xmlHttp;
}
export const httpDelete = (url) => {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open('DELETE', url, false);
    xmlHttp.send(null);

    return xmlHttp;
}
