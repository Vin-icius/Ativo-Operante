document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("loginForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Previne o envio padrão do formulário

        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;

        var user = {
            email: email,
            password: password
        };

        const url = 'http://localhost:8080/apis/security/login';
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });

            if (response.ok) {
                const token = await response.text();

                // Decode the token payload to get user details
                const payload = JSON.parse(atob(token.split('.')[1]));
                const nivel = parseInt(payload.nivel);

                // Armazenar o token e o email no localStorage
                localStorage.setItem('token', token);
                localStorage.setItem('email', email); // Armazena o email

                // Fetch the user ID using the new endpoint
                const userIdResponse = await fetch(`http://localhost:8080/apis/security/get-id-by-email?email=${email}`);
                if (userIdResponse.ok) {
                    const userId = await userIdResponse.json();
                    localStorage.setItem('userId', userId); // Armazena o userId

                    if (nivel === 1) {
                        //window.location.href = 'home-administrador.html';
                    } else if (nivel === 2) {
                        window.location.href = "../html/indexCitizen.html";
                    } else {
                        alert('Nível de usuário desconhecido.');
                    }
                } else {
                    alert("Erro ao obter ID do usuário.");
                }
            } else {
                const errorMessage = await response.text();
                alert(errorMessage);
            }
        } catch (error) {
            console.error('Error:', error);
            alert("An error occurred. Please try again later.");
        }
    });
});
