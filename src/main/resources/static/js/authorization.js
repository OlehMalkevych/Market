import {httpGet, httpDelete, httpPut, httpPost} from './tools/http-tools.js'

localStorage.setItem('isAuthorize', 'inactive');

document.addEventListener('DOMContentLoaded', function () {
        loginButtonOnClick();
        registerButtonClick();
});

const registerButtonClick = () => {
    let registerButton = document.getElementById('register-user-button');

    let loginInput = document.getElementById('user-register-login-input');
    let passwordInput = document.getElementById('user-register-password-input');
    let usernameInput = document.getElementById('user-register-username-input');



    registerButton.onclick = () => {

        console.log("password " + passwordInput.value);
        console.log("login " + loginInput.value);
        console.log("username " + usernameInput.value);

        const login = loginInput.value;
        const password = passwordInput.value;
        const username = usernameInput.value;

        if (login && password && username) {
            const user = {
                login,
                password,
                username
            }
            let request = httpPost("http://localhost:8080/users/register", JSON.stringify(user));

            if (request.status === 200) {

                let response = JSON.parse(request.response);
                localStorage.setItem('user_token', response.token);
                localStorage.setItem('user_name', response.username);
                localStorage.setItem('user_id', response.id);
                localStorage.isAuthorize = 'active';


                window.open('/', '_self');
            }
        }
    }
}

const loginButtonOnClick = () => {

    let loginButton = document.getElementById('login-user-button');

    let loginInput = document.getElementById('user-login-input');
    let passwordInput = document.getElementById('user-login-password-input');
    loginButton.onclick = () => {
        const login = loginInput.value;
        const password = passwordInput.value;

        if (login && password) {
            const user = {
                login,
                password
            }
            let request = httpPost("http://localhost:8080/users/login", JSON.stringify(user));

            if (request.status === 200) {

                let response = JSON.parse(request.response);
                localStorage.setItem('user_token', response.token);
                localStorage.setItem('user_name', response.username);
                localStorage.setItem('user_id', response.id);
                localStorage.isAuthorize = '';
                localStorage.isAuthorize = 'active';
                console.log(localStorage.isAuthorize);

                window.open('/', '_self');
            }
        }
    }
}


