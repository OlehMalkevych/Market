import {httpDelete, httpGet, httpPost, httpPut} from '../tools/http-tools.js'
// import {appendHeader} from './header.js'
import {Modal} from './modal.js'
let itemList = {};

document.addEventListener('DOMContentLoaded', function () {
    // appendHeader().then(r => {
        loadShop();
        loadItems();
        mapAndLoad_ItemsFromResponse();
        sortingSelectOnChange();
        nextPageButtonOnClick();
        prevPageButtonOnClick();
        createItemButtonOnClick();
        actionsOnFileLoad();
        actionsOnAddImageClick();
        openModal();
    // });

});

let shop = {
    id: -1,
    name: '',
    categories: []
};
let image = null;

const pagination = {
    page: 0,
    size: 10,
    totalPages: 0,
    totalElements: 0
}

// LOAD SHOP

const loadShop = () => {
    // const params = new URLSearchParams(window.location.search);
    const params = new URLSearchParams(window.location.search);
    console.log(params.get('shopId'));
    if (params.has('shopId')) {
        let shopId = params.get('shopId');
        let request = httpGet("http://localhost:8080/shops/" + shopId);
        console.log(itemList);
        if (request.status === 200) {
            shop = JSON.parse(request.response);
            console.log(shop);
            appendShopData();
            mapAndLoad_ItemsFromResponse();
        }
    }
}
const mapAndLoad_ItemsFromResponse = () => {
    let request = httpGet("http://localhost:8080/items/getAllItems");

    let items = JSON.parse(request.response);
    if (request.status === 200) {
        if (items.length > 0){
            for (let index in items){
                itemList[items[index].id] = items[index];
            }
        }
        console.log(itemList);
        editeItemButtonOnClick();
    }
}


// BUTTONS

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
            shopId: shop.id,
            image: image
        }

        console.log(item);
        let request = httpPost('http://localhost:8080/items', JSON.stringify(item));
        if (request.status === 200) {
            // window.location.reload();
            nameInput = '';
            priceInput = '';
            countInput = '';
            descriptionInput = '';
            loadItems();
        }
    }
}

const editeItemButtonOnClick = () => {
    let editButton = document.getElementsByClassName('create-item-button');

    for (let i = 0; i < editButton.length; i++) {
        let button = editButton[i];
        let itemId = button.getAttribute('itemid');
        console.log(itemId)

        editButton.onclick = () => {
            console.log('here')

            let nameInput = document.getElementById('item-name-input');
            let priceInput = document.getElementById('item-price-input');
            let countInput = document.getElementById('item-count-input');
            let descriptionInput = document.getElementById('item-description-input');

            nameInput.value = itemList[itemId].name;
            console.log(nameInput.value)
            priceInput.value = itemList[itemId].price;
            countInput.value = itemList[itemId].count;
            descriptionInput.value = itemList[itemId].description;

            const editItem = {
                name: nameInput.value,
                price: +priceInput.value,
                count: +countInput.value,
                description: descriptionInput.value,
                shopId: shop.id,
                image: image
            }

            editButton.innerText = 'Save';
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
//
// NAVIGATION BUTTON
//
const changePageNavigation = () => {
    let pageLabel = document.getElementById('page-number-label');
    let nextPageBtn = document.getElementById('next-page-button');
    let prevPageBtn = document.getElementById('prev-page-button');

    pageLabel.innerText = pagination.page + 1;

    if (pagination.totalPages - pagination.page === 1) {
        nextPageBtn.disabled = true;
    } else {
        nextPageBtn.disabled = false;
    }

    if (pagination.page === 0) {
        prevPageBtn.disabled = true;
    } else {
        prevPageBtn.disabled = false;
    }
}
//
// IMAGE

const actionsOnAddImageClick = () => {
    let button = document.getElementById('add-image-button');
    let fileInput = document.getElementById('item-image-input');
    button.onclick = () => {
        fileInput.click()
    }
}

const actionsOnFileLoad = () => {
    let fileInput = document.getElementById('item-image-input');
    fileInput.onchange = (event) => {
        console.log(event.target.files[0]);
        getBase64FromFile(event.target.files[0])
            .then(data => {
                image = data;
                let imagePreview = document.getElementById('image-preview');
                imagePreview.setAttribute('src', image);
                imagePreview.hidden = false;
            });
    }
}

const getBase64FromFile = (file) => {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
};

const appendShopData = () => {
    const shopNameLabel = document.getElementById('shop-name-label');
    shopNameLabel.innerText = shop.name;

    const shopCategoriesContainer = document.getElementById('shop-categories-container');
    shopCategoriesContainer.innerHTML = '';

    if (shop.categories && shop.categories.length > 0) {
        for (let category of shop.categories) {
            shopCategoriesContainer.innerHTML = shopCategoriesContainer.innerHTML + getCategoryWrapper(category)
        }
    } else {
        shopCategoriesContainer.innerHTML = `<span class="hor-space id-text">no categories</span>`;
    }
}
//
// SORTING
//
const sortingSelectOnChange = () => {
    let sortingSelect = document.getElementById('sort-field-select');
    sortingSelect.onchange = () => {
        pagination.page = 0
        loadItems();
    }
}
//
// ITEM
//
// http://localhost:8080/items?shopId=7&pagination.page=1&pagination.size=5
const loadItems = () => {
    let sortingSelect = document.getElementById('sort-field-select');
    let url = "http://localhost:8080/items?shopId=" + shop.id
        + '&pagination.page=' + pagination.page
        + '&pagination.size=' + pagination.size
        + sortingSelect.value;
    console.log(url);
    let request = httpGet(url);
    if (request.status === 200) {
        let response = JSON.parse(request.response);
        pagination.totalElements = response.totalElements;
        pagination.totalPages = response.totalPages;
        // console.log(response);
        mapItemsToTable(response.data);
        changePageNavigation();
    }
}

const mapItemsToTable = (items) => {
    if (items.length > 0) {
        appendItemsTable();
        for (let index in items) {
            if (items[index]) {
                insertItemToTable(items[index]);
            }
        }

    }
}


const insertItemToTable = (item) => {
    let table = document.getElementById('items-table');
    let imageUrl = item.image ? 'http://localhost:8080/image/' + item.image : 'https://cdn3.vectorstock.com/i/1000x1000/21/87/blank-item-icon-template-with-red-big-sale-top-vector-32662187.jpg';
    table.innerHTML = table.innerHTML +
        `<tr>
        <td class = "right-content id-text" >${item.id} </td>
        <td class="left-content left-inner-space"> ${item.name}</td>
        <td class="right-content">${item.price}₴</td>
        <td class="right-content">${item.count}</td>
        <td class="description-content">${item.description}</td>
        <td class="right-content"><img src="${imageUrl}" alt=""></td>
        <td class="right-content">
              <button class="item-edit-btn edit-btn" itemid="${item.id}">Edit</button>
            <button class="item-delete-btn dark-btn" itemid="${item.id}">Delete</button>
        </td>
        </tr>`
}

const appendItemsTable = () => {
    let container = document.getElementById('items-container');
    container.innerHTML =
        `<table id="items-table">
<th class="right-content">id</th>
<th class="left-content left-inner-space">name</th>
<th class="right-content">price</th>
<th class="right-content">count</th>
<th class="description-content">description</th>
</table>`
}
//
// CATEGORY
//
const getCategoryWrapper = (category) => {
    return `<span class="category-wrapped">${category.name}</span>`
}

const getShopId = () => {
    const pathnameSections = window.location.pathname.split('/');
    return pathnameSections[pathnameSections.length - 1];
}

const categoryContainsById = (id) => {
    for (let category of shop.categories) {
        console.log(category.id + ' and found for ' + id);
        if (category.id === id) {
            return true;
        }
    }
    return false;
}
//
// CATEGORY_MODAL
//
const openModal = () => {
    document.getElementById('open-categories-modal').onclick = () => {
        let modal = new Modal(
            'categories',
            getCategoriesModal()
        );
        modal.open();
        let checkboxes = document.getElementsByClassName('shop-category-check');
        for (let checkbox of checkboxes) {
            if (categoryContainsById(checkbox.value)) {
                checkbox.checked = true;
            }
        }

        let saveCategoriesButton = document.getElementById('save-categories-button');

        saveCategoriesButton.onclick = () => {

            const shopRequest = {
                name: shop.name,
                categoryIds: []
            }
            for (let checkbox of checkboxes) {
                if (checkbox.checked) {
                    shopRequest.categoryIds.push(+checkbox.value);
                }
            }
            httpPut('http://localhost:8080/shops/' + shop.id, JSON.stringify(shopRequest));
            modal.close();
            loadShop();
        }
    };
}

const getCategoriesModal = () => {
    let request = httpGet('http://localhost:8080/categories');
    let response = JSON.parse(request.response);

    let modal = `<div class="modal-content-container small-modal">
                <div class="modal-header">Categories</div>
                <div class="modal-content min-h120" id="categories-container">`;
    for (let category of response) {
        modal  = modal + getWrapperForCategoryInModal(category);
    }
       modal = modal +
           `</div>
                <div class="modal-footer">
                <button id="save-categories-button" class="dark-btn modal-save-button">Save</button>
                </div>
            </div>`
    return modal;
}

const getWrapperForCategoryInModal = (category) => {
    return `<div class="check-element flex-container vertical-center">
                <input class="check-input shop-category-check" id="${category.id + '-category'}" type="checkbox" value="${category.id}">
                <label class="checkbox-label" for="${category.id + '-category'}">${category.name}</label>
            </div>`
}