document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Previne o envio padrão do formulário


        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;

        var user = {
            email: email,
            password: password
        };

        const url = 'http://localhost:8080/apis/security/login';
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => {
                if (response.ok) {
                    alert("Login successful!");
                    window.location.href = "../html/indexCitizen.html";
                } else {

                    response.text().then(errorMessage => {
                        alert(errorMessage);
                    });
                }
            })
            .catch(error => {
                // Se ocorrer algum erro durante a solicitação, exibe uma mensagem de erro genérica
                console.error('Error:', error);
                alert("An error occurred. Please try again later.");
            });
    });
});
