import {httpGet, httpDelete, httpPut, httpPost} from './http-tools.js'
// import {appendHeader} from './header.js'

document.addEventListener('DOMContentLoaded', function () {
    // appendHeader().then(r => {


    registerButtonClick();
    // });
});

const registerButtonClick = () => {
    let registerButton = document.getElementById('register-user-button');

    let loginInput = document.getElementById('user-login-input');
    let passwordInput = document.getElementById('user-password-input');
    let usernameInput = document.getElementById('user-username-input');

    registerButton.onclick = () => {
        const login = loginInput.value;
        const password = passwordInput.value;
        const username = usernameInput.value;

        if (login && password && username) {
            const user = {
                login,
                password,
                username
            }
            let request = httpPost("http://localhost:8080/users/register",JSON.stringify(user));
            if (request.status === 200) {

                console.log('here')
                let response = JSON.parse(request.response);
                localStorage.setItem('user_token', response.token);
                localStorage.setItem('user_name', response.username);
                localStorage.setItem('user_id', response.id);

                window.open('/', '_self');
                localStorage.clear();
            }
        }
    }


}
