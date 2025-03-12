const passwordInput = document.getElementById('password');
const btn = document.getElementById('btn-toggle');


btn.addEventListener('click', () => {
    if (passwordInput.type == 'password') {
        passwordInput.type = 'text';
        btn.textContent = 'Hide';
    } else {
        passwordInput.type = 'password';
        btn.textContent = 'Show';
    }
});
