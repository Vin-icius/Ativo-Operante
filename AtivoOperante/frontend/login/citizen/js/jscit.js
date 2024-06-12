document.addEventListener("DOMContentLoaded", function() {
    // Função para preencher o menu suspenso de órgãos com os órgãos já cadastrados
    function preencherOrgaos() {
        fetch('http://localhost:8080/apis/citizen/get-all-agencies')
            .then(response => response.json())
            .then(data => {
                var selectOrgao = document.getElementById("orgao");
                selectOrgao.innerHTML = '';

                data.forEach(orgao => {
                    var option = document.createElement("option");
                    option.value = orgao.id;
                    option.text = orgao.name;
                    selectOrgao.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    preencherOrgaos();

    function preencherTipos() {
        fetch('http://localhost:8080/apis/citizen/get-all-types')
            .then(response => response.json())
            .then(data => {
                var selectTipo = document.getElementById("tipo");
                selectTipo.innerHTML = '';

                data.forEach(tipo => {
                    var option = document.createElement("option");
                    option.value = tipo.id;
                    option.text = tipo.name;
                    selectTipo.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    preencherTipos();

    function enviarDenuncia() {
        var titulo = document.getElementById("titulo").value;
        var descricao = document.getElementById("descricao").value;
        var urgencia = document.getElementById("urgencia").value;
        var orgaoId = document.getElementById("orgao").value;
        var tipoId = document.getElementById("tipo").value;

        var userId = localStorage.getItem('userId');

        if (!userId) {
            alert("Usuário não está logado.");
            return;
        }

        var params = new URLSearchParams();
        params.append("title", titulo);
        params.append("text", descricao);
        params.append("urgency", urgencia);
        params.append("org_id", orgaoId);
        params.append("tip_id", tipoId);
        params.append("usu_id", userId);

        fetch('http://localhost:8080/apis/citizen/add-complaint', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                alert("Denúncia enviada com sucesso!");
                fetchDenuncias();
            })
            .catch(error => {
                console.error('Erro ao enviar denúncia:', error);
                alert("Erro ao enviar denúncia. Por favor, tente novamente.");
            });
    }

    document.getElementById("enviarDenunciaBtn").addEventListener("click", enviarDenuncia);

    function fetchDenuncias() {
        var email = localStorage.getItem('email');

        if (!email) {
            alert("Usuário não está logado.");
            return;
        }

        fetch(`http://localhost:8080/apis/citizen/get-complaints-by-email?email=${email}`)
            .then(response => response.json())
            .then(data => {
                var listaDenuncias = document.getElementById("listaDenuncias");
                listaDenuncias.innerHTML = '';

                data.forEach(denuncia => {
                    var listItem = document.createElement("li");
                    var agencyName = denuncia.agency ? denuncia.agency.name : "N/A";
                    var tipoName = denuncia.type ? denuncia.type.name : "N/A";
                    var feedbackText = denuncia.feedback ? denuncia.feedback.text : "Sem feedback"; // Verifica se há feedback
                    listItem.textContent = `Título: ${denuncia.title}, Descrição: ${denuncia.text}, Urgência: ${denuncia.urgency}, Órgão: ${agencyName}, Tipo: ${tipoName}, Feedback: ${feedbackText}`; // Inclui o feedback no texto
                    listaDenuncias.appendChild(listItem);
                });
            })
            .catch(error => {
                console.error('Erro ao buscar denúncias:', error);
            });
    }

    fetchDenuncias();
});
