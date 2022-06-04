
document.addEventListener('DOMContentLoaded', function(){
    loadShops();
});

const loadShops = () => {
    let request = httpGet("http://localhost:8080/shops");
    console.log(request);
    let response = JSON.parse(request.response);
    console.log(response);
    if (request.status === 200){

    }
}

const mapShopsFromResponse = () => {

}


const httpGet = (url) => {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open('GET', url, false);
    xmlHttp.send(null);

    return xmlHttp;
}


