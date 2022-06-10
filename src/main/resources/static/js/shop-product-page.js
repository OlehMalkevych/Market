import {httpGet, httpDelete, httpPut, httpPost} from './tools/http-tools.js'
import {appendHeader} from './headerAndFoter/header.js'
import {appendFooter} from './headerAndFoter/footer.js'



document.addEventListener('DOMContentLoaded', function () {
    appendHeader().then(h => {
        appendFooter().then(f => {
            loadShop();
            loadItems();
            // nextPageButtonOnClick();
            // prevPageButtonOnClick();
            // sortingSelectOnChange();

        })
    });
});

let shop = {
    id: -1,
    name: '',
    categories: []
};
let image = null;

const pagination = {
    page: 0,
    size: 100,
    totalPages: 0,
    totalElements: 0
}

const loadShop = () => {

    // let request = httpGet("http://localhost:8080/shops/" + getShopId);
    //

    // console.log(getShopId())
    // const params = new URLSearchParams(window.location.search);

    console.log('here')
        let request = httpGet("http://localhost:8080/shops/" + getShopId());
    console.log('here')

    if (request.status === 200) {
            shop = JSON.parse(request.response);
            console.log(shop);
            appendShopData();

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

const getShopId = () => {
    const pathnameSections = window.location.pathname.split('/');
    return pathnameSections[pathnameSections.length - 1];
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

const appendShopData = () => {
    const shopNameLabel = document.getElementById('shop-name');
    shopNameLabel.innerText = shop.name;

    const shopCategoriesContainer = document.getElementById('category-container');
    shopCategoriesContainer.innerHTML = '';

    if (shop.categories && shop.categories.length > 0) {
        for (let category of shop.categories) {
            shopCategoriesContainer.innerHTML = shopCategoriesContainer.innerHTML
                + `<li><a href="#">${category.name}</a></li>`;
        }
    } else {
        shopCategoriesContainer.innerHTML = `<span>Categories list is empty</span>`;
    }
}

// const nextPageButtonOnClick = () => {
//     let nextPageBtn = document.getElementById('next-page-button');
//     nextPageBtn.onclick = () => {
//         pagination.page = pagination.page + 1;
//         loadItems();
//     }
// }

// const prevPageButtonOnClick = () => {
//     let prevPageBtn = document.getElementById('prev-page-button');
//     prevPageBtn.onclick = () => {
//         pagination.page = pagination.page - 1;
//         loadItems();
//     }
// }
//
// NAVIGATION BUTTON
//
// const changePageNavigation = () => {
//     let pageLabel = document.getElementById('page-number-label');
//     let nextPageBtn = document.getElementById('next-page-button');
//     let prevPageBtn = document.getElementById('prev-page-button');
//
//     pageLabel.innerText = pagination.page + 1;
//
//     if (pagination.totalPages - pagination.page === 1) {
//         nextPageBtn.disabled = true;
//     } else {
//         nextPageBtn.disabled = false;
//     }
//
//     if (pagination.page === 0) {
//         prevPageBtn.disabled = true;
//     } else {
//         prevPageBtn.disabled = false;
//     }
// }
// const sortingSelectOnChange = () => {
//     let sortingSelect = document.getElementById('sort-field-select');
//     sortingSelect.onchange = () => {
//         pagination.page = 0
//         loadItems();
//     }
// }

// http://localhost:8080/items?shopId=7&pagination.page=1&pagination.size=5
const loadItems = () => {
    let sortingSelect = document.getElementById('sort-field-select');
    let url = "http://localhost:8080/items?shopId=" + shop.id
        + '&pagination.page=' + pagination.page
        + '&pagination.size=' + pagination.size;
    // + sortingSelect.value;

    let request = httpGet(url);
    if (request.status === 200) {
        let response = JSON.parse(request.response);
        pagination.totalElements = response.totalElements;
        pagination.totalPages = response.totalPages;
        // console.log(response);
        mapItemsToContainer(response.data);
        // changePageNavigation();
    }
}

const mapItemsToContainer = (items) => {
    if (items.length > 0) {
        for (let index in items) {
            if (items[index]) {
                insertItemToContainer(items[index]);
            }
        }
    }
}

const insertItemToContainer = (item) => {
    let container = document.getElementById('items-container');
    let imageUrl = item.image ? 'http://localhost:8080/image/' + item.image : 'https://cdn3.vectorstock.com/i/1000x1000/21/87/blank-item-icon-template-with-red-big-sale-top-vector-32662187.jpg';
    
    let url = 'http://localhost:8080/cart/add-item' + JSON.stringify();

    container.innerHTML = container.innerHTML +
        `
       <div class="element-in-item">
        <div>
            <img style="height:300px; width: 250px" src="${imageUrl}" alt="">
        </div>
        <div class="items-sub-text">
            <div>${item.name}</div>
            <div>${item.price}₴</div>
        </div>
        <div><a href="${url}"><button itemid="${item.id}">Add to Cart</button></a></div>
       </div>
        
        `
}

const sendItemToCart = () => {

}