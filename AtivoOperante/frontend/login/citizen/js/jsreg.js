document.getElementById('user-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const cpf = document.getElementById('cpf').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const password2 = document.getElementById('password2').value;
    const emailError = document.getElementById('email-error');
    const password2Error = document.getElementById('password2-error');
    const passwordError = document.getElementById('password-error');
    const cpfError = document.getElementById('cpf-error');
    const message = document.getElementById('message');

    // Limpar mensagens de erro anteriores
    emailError.textContent = '';
    cpfError.textContent = '';
    passwordError.textContent = '';
    password2Error.textContent = '';
    message.textContent = '';

    if (password !== password2) {
        password2Error.textContent = "As senhas não coincidem.";
        return;
    }

    const user = {
        cpf: cpf,
        email: email,
        password: password,
        nivel: 1  // Define um valor padrão para o nível, se necessário.
    };

    const url = 'http://localhost:8080/apis/citizen/add-user';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            const result = await response.json();
            message.textContent = 'Usuário cadastrado com sucesso!';
            message.style.color = 'green';
            window.location.href = "../html/index.html";
        } else {
            const error = await response.text();
            if (error.includes("Email inválido")) {
                emailError.textContent = error;
            } else if (error.includes("Senha inválida")) {
                passwordError.textContent = error;
            } else if (error.includes("CPF inválido")) {
                cpfError.textContent = error;
            } else {
                message.textContent = `Erro: ${error}`;
                message.style.color = 'red';
            }
        }
    } catch (error) {
        message.textContent = `Erro: ${error.message}`;
        message.style.color = 'red';
    }
});
