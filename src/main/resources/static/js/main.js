import {httpGet, httpDelete, httpPut, httpPost} from './tools/http-tools.js'
import {appendHeader} from './headerAndFoter/header.js'
import {appendFooter} from './headerAndFoter/footer.js'

let shopList = {};

document.addEventListener('DOMContentLoaded', function () {
    appendHeader().then(h => {
        appendFooter().then(f => {
            loadShops();
        })
    });
});

const loadShops = () => {
    let request = httpGet("http://localhost:8080/shops");

    let response = JSON.parse(request.response);
    if (request.status === 200) {
        mapShopsFromResponse(response);
        if(localStorage.getItem('isAuthorize') === 'active'){
            let name = document.getElementById('user-name-text');

            name = localStorage.getItem('user_name');

            let authorizationContainer = document.getElementById('user-authorization-container');

            authorizationContainer.innerHTML = authorizationContainer.innerHTML +
                `
                <div class="before-user-text" id="before-user-text">
                     <p id="before-user-text">user: </p>
                 </div>
                 <div class="user-text" id="user-text">
                     <p class="user-name-text" id="user-name-text">${name}</p>
                 </div>
                 <div class="after-user-text" id="after-user-text">
                     <a id="exit-link" href="http://localhost:8080/authorization"><img id="exit-image"
                                                                                       style="width: 20px; height: 20px"
                                                                                       src="../images/exit-img.png"
                                                                                       alt="" srcSet=""></a>
                 </div>
                `
            clickOnExitLink();

        } else {

            let authorizationContainer = document.getElementById('user-authorization-container');

            authorizationContainer.innerHTML = authorizationContainer.innerHTML +
                `
                <div class="auth-text" id="auth-text"><a href="http://localhost:8080/authorization">
                    <p class="authorization-text" id="authorization-text">Authorization</p>
                 </a></div>        
                `
        }
    }
}

const clickOnExitLink = () => {
  let exitLink = document.getElementById('exit-link');
  let exitImg = document.getElementById('exit-image');
  let before_user_text = document.getElementById('before-user-text');

  exitLink.onclick = () => {
      localStorage.isAuthorize = 'inactive';
      exitImg.hidden=true;
      before_user_text.value = '';
      localStorage.setItem('user_id', '');
      localStorage.setItem('user_name', '');
      localStorage.setItem('user_token', '');
  }
}

const mapShopsFromResponse = (shops) => {
    if (shops.length > 0) {
        for (let index in shops) {
            appendShopToContainer(shops[index]);
            shopList[shops[index].id] = shops[index];
        }
        // addOnClickActionEditButtons();
        // addOnClickActionDeleteButtons();
    } else {
        let container = document.getElementById('shops-table-container');
        container.innerHTML = `<h1>Shop list is empty</h1>`
    }
}

const appendShopToContainer = (shop) => {
    let container = document.getElementById('shops-container');
    let url = 'http://localhost:8080/shopItems/' + shop.id;

    container.innerHTML = container.innerHTML +
        `
        <div class="shop-container">
            <a target="_self" class="product-link" href="${url}"><img src="../../images/marketImage.png"></a>
            <p>${shop.name}</p>
        </div>`
}


