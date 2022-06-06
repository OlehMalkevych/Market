import {httpPost, httpPut, httpGet, httpDelete} from './http-tools.js'

document.addEventListener('DOMContentLoaded', function(){
    loadShop();
    nextPageButtonOnClick();
    prevPageButtonOnClick();
    sortingSelectedOnChange();
    createItemButtonOnClick();
});

let shop = {
    id: -1,
    name:''
};

let pagination = {
    page:0,
    size:2,
    totalPages:0,
    totalElements:0
}

const loadShop = () => {
    const params = new URLSearchParams(window.location.search);
    console.log(params.get('shopId'));
    if (params.has('shopId')){
        let shopId = params.get('shopId');
        let request = httpGet("http://localhost:8080/shops/" + shopId);

        console.log()
        if (request.status === 200){
            let response = JSON.parse(request.response);
            const shopNameLabel = document.getElementById('shop-name-label');
            shopNameLabel.innerText = response.name;
            shop = response;
            loadItems();
        }
    }
}

const createItemButtonOnClick = () => {
    let createButton = document.getElementById('create-item-button');

    createButton.onclick = () => {
        let nameInput = document.getElementById('item-name-input');
        let priceInput = document.getElementById('item-price-input');
        let countInput = document.getElementById('item-count-input');
        let descriptionInput = document.getElementById('item-description-input');

        const item = {
            name: nameInput.value,
            price: +priceInput.value,
            count: +countInput.value,
            description: descriptionInput.value,
            shopId: shop.id
        }
        console.log(item);
        let request = httpPost('http://localhost:8080/items', JSON.stringify(item));

        if (request.status === 200){
            nameInput = '';
            priceInput = '';
            countInput = '';
            descriptionInput = '';
            window.location.reload();
        }
    }
}

const nextPageButtonOnClick = () => {
    let nextPageBtn = document.getElementById('next-page-button');
    nextPageBtn.onclick = () => {
        pagination.page = pagination.page + 1;
        loadItems();
    }
}

const prevPageButtonOnClick = () => {
    let prevPageBtn = document.getElementById('prev-page-button');
    prevPageBtn.onclick = () => {
        pagination.page = pagination.page - 1;
        loadItems();
    }
}

const changePageNavigation = () => {
    let pageLabel = document.getElementById('page-number-label');
    let nextPageButton = document.getElementById('next-page-button');
    let prevPageButton = document.getElementById('prev-page-button');

    pageLabel.innerText = pagination.page + 1;

    //disable btn if end
    nextPageButton.disabled = pagination.totalPages - pagination.page === 1;
    //disable btn if start
    prevPageButton.disabled = pagination.page === 0;

}
const sortingSelectedOnChange = () =>{
    let sortingSelect = document.getElementById('sort-field-select');
    sortingSelect.onchange = () => {
        pagination.page = 0;
        loadItems();
    }
}

//localhost:8080/items?shopId=1&maxPrice= 500&pagination.page=0&pagination.size=2&pagination.field=price&pagination.direction=DESC
const loadItems = () => {
    let sortingSelect = document.getElementById('sort-field-select');
    let url = "http://localhost:8080/items?shopId=" + shop.id
    + '&pagination.page=' + pagination.page
    + '&pagination.size=' + pagination.size + sortingSelect.value;

    let request = httpGet(url);
    if (request.status === 200){
        let response = JSON.parse(request.response);
        pagination.totalElements = response.totalElements;
        pagination.totalPages = response.totalPages;
        mapItemsToTable(response.data);
        changePageNavigation();

    }
}

const mapItemsToTable = (items) => {
    if (items.length > 0) {
        appendItemsTable()
        for (let index in items){
            if (items[index]){
                insertItemToTable(items[index]);
            }
        }
    }
}
const insertItemToTable = (item) => {
    let table = document.getElementById('items-table');
    let url = 'http://localhost:8080/admin/shop-manage?shopId=' + shop.id;

    table.innerHTML = table.innerHTML +
        `<tr>
        <td class="">${item.id}</td>
        <td class="">${item.name}</td>
        <td class="">${item.price}</td>
        </tr>`
}
const appendItemsTable = () => {
    let container = document.getElementById('items-container');


    container.innerHTML =
        `<table id="items-table">
            <th class="right-content">id</th>
            <th class="left-content left-inner-space ">name</th>
            <th class="right-content">price</th>
        </table>`
}