import {httpGet, httpDelete, httpPut, httpPost} from './http-tools.js'
// import {appendHeader} from './header.js'

document.addEventListener('DOMContentLoaded', function () {
    // appendHeader().then(r => {
    // });
    localStorage.clear();
    loginButtonOnClick();
});

const loginButtonOnClick = () => {
    let loginButton = document.getElementById('login-user-button');

    let loginInput = document.getElementById('user-login-input');
    let passwordInput = document.getElementById('user-password-input');

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
                window.open('/', '_self');
            }
        }
    }
}