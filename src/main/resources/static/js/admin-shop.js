import {httpPost, httpPut, httpGet, httpDelete} from './http-tools.js'
let shopList = {};

document.addEventListener('DOMContentLoaded', function(){
    loadShops();
    setCreateShopButtonOnClick();
});

const setCreateShopButtonOnClick = () => {
    let button = document.getElementById('create-shop-button');

    button.onclick = () => {
        let shopIdInput = document.getElementById('shop-id-input');
        let shopNameInput = document.getElementById('shop-name-input');
        let shopCreateButton = document.getElementById('create-shop-button');

        if (shopNameInput.value){
            if (!shopIdInput.value){
                const shop = {
                    name: shopNameInput.value
                }
                let request = httpPost("http://localhost:8080/shops", JSON.stringify(shop))
                if (request.status === 200){
                    shopNameInput.value = '';
                    loadShops();
                }
            } else {
                const shop = {
                    name: shopNameInput.value
                }
                let request = httpPut("http://localhost:8080/shops/" + shopIdInput.value, JSON.stringify(shop))
                if (request.status === 200){
                    shopNameInput.value = '';
                    loadShops();
                }
                shopCreateButton.innerText = 'Create';
            }



        }
    }
}

const loadShops = () => {
    let request = httpGet("http://localhost:8080/shops");

    let response = JSON.parse(request.response);
    console.log(response);
    if (request.status === 200) {
        mapShopsFromResponse(response);
    }
}

const mapShopsFromResponse = (shops) => {
    if (shops.length > 0){
        appendShopsTable();
        for (let index in shops){
            appendShopToTable(shops[index]);
            shopList[shops[index].id] = shops[index];
        }
        addOnClickActionEditButtons();
        addOnClickActionDeleteButtons();
    } else {
        let container = document.getElementById('shops-table-container');
        container.innerHTML = `<h1> Shop list is empty</h1>`
    }
}
const mapShopsFromList = () => {
    if (shopList){
        appendShopsTable();
        for (let index in shopList){
            if (shopList[index]){
                appendShopToTable(shopList[index]);
            }
        }
        addOnClickActionEditButtons();
        addOnClickActionDeleteButtons();
    } else {
        let container = document.getElementById('shops-table-container');
        container.innerHTML = `<h1> Shop list is empty</h1>`
    }
}

const appendShopsTable = () => {
    let container = document.getElementById('shops-table-container');
    container.innerHTML =
        `<table id="shops-table">
            <th>id</th>
            <th>name</th>
            <th></th>
        </table>`
}

const appendShopToTable = (shop) => {
    let table = document.getElementById('shops-table');
    let url = 'http://localhost:8080/admin/shop-manage/' + shop.id;

    table.innerHTML = table.innerHTML +
        `<tr>
        <td class="right-content id-text">${shop.id}</td>
        <td class="left-content left-inner-space">${shop.name}</td>
        <td class="right-content">
          <td> <a target="_blank" href="${url}">Details</a></td>
          <td><button class="shop-edit-btn" shopid="${shop.id}">Edit</button></td>
          <td><button class="shop-delete-btn" shopid="${shop.id}">Delete</button></td>
        </td>
        </tr>`
}

const addOnClickActionEditButtons = () => {
    let buttons = document.getElementsByClassName('shop-edit-btn');
    for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        let shopId = button.getAttribute('shopid');

        button.onclick = () => {
            let shopIdInput = document.getElementById('shop-id-input');
            let shopNameInput = document.getElementById('shop-name-input');
            let shopCreateButton = document.getElementById('create-shop-button');
            shopIdInput.value = shopId;
            shopNameInput.value = shopList[shopId].name;
            shopCreateButton.innerText = 'Save';
        }

    }
}
const addOnClickActionDeleteButtons = () => {
    let buttons = document.getElementsByClassName('shop-delete-btn');
    for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        let shopId = button.getAttribute('shopid');

        button.onclick = () => {
           let request = httpDelete("http://localhost:8080/shops/" + shopId);
            if (request.status === 200) {
                delete shopList[shopId];
                mapShopsFromList()
            }
        }

    }
}

