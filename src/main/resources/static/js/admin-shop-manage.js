import {httpPost, httpPut, httpGet, httpDelete} from './http-tools'

document.addEventListener('DOMContentLoaded', function(){
    console.log('here')
    // loadShop();
    console.log('here')

});

const loadShop = () => {
    const params = new URLSearchParams(window.location.search);
    if (params.has('shopId')){
        let shopId = params.get('shopId');
        let request = httpGet("http://localhost:8080/shops/" + shopId);

        if (request.status === 200){
            let response = JSON.parse(request.response);
            const shopNameLabel = document.getElementById('shop-name-label');
            console.log('here')
            shopNameLabel.innerText = response.name;
        }
    }
}