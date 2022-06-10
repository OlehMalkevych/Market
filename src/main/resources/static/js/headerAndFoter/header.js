export const appendHeader = async () => {
    await fetch('../../html/headerAndFooter/header.html')
        .then(response => response.text())
        .then(text => {
            document.body.innerHTML = text + document.body.innerHTML;
        });
}

