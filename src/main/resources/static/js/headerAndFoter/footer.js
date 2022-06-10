export const appendFooter = async () => {
    await fetch('../../html/headerAndFooter/footer.html')
        .then(response => response.text())
        .then(text => {
            document.body.innerHTML = document.body.innerHTML + text;
        });
}

