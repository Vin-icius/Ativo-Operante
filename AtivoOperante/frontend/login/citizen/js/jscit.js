document.addEventListener("DOMContentLoaded", function() {
    // Função para preencher o menu suspenso de órgãos com os órgãos já cadastrados
    function preencherOrgaos() {
        fetch('http://localhost:8080/apis/citizen/get-all-agencies') // Ajuste a rota para corresponder à sua implementação no backend
            .then(response => response.json())
            .then(data => {
                // Limpar o menu suspenso de órgãos
                var selectOrgao = document.getElementById("orgao");
                selectOrgao.innerHTML = '';

                // Preencher o menu suspenso com os órgãos retornados
                data.forEach(orgao => {
                    var option = document.createElement("option");
                    option.value = orgao.id;
                    option.text = orgao.name;
                    selectOrgao.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                // Tratar o erro, se necessário
            });
    }

    // Chamar a função para preencher o menu suspenso de órgãos quando a página é carregada
    preencherOrgaos();

    // Função para preencher os tipos de problema
    function preencherTipos() {
        fetch('http://localhost:8080/apis/citizen/get-all-types') // Ajuste a rota para corresponder à sua implementação no backend
            .then(response => response.json())
            .then(data => {
                // Limpar o menu suspenso de tipos de problema
                var selectTipo = document.getElementById("tipo");
                selectTipo.innerHTML = '';

                // Preencher o menu suspenso com os tipos de problema retornados
                data.forEach(tipo => {
                    var option = document.createElement("option");
                    option.value = tipo.id;
                    option.text = tipo.name;
                    selectTipo.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                // Tratar o erro, se necessário
            });
    }

    // Chamar a função para preencher os tipos de problema quando a página é carregada
    preencherTipos();

    // Função para enviar a denúncia
    function enviarDenuncia() {
        var titulo = document.getElementById("titulo").value;
        var descricao = document.getElementById("descricao").value;
        var urgencia = document.getElementById("urgencia").value;
        var orgaoId = document.getElementById("orgao").value;
        var tipoId = document.getElementById("tipo").value;
        var imagem = document.getElementById("imagem").files[0]; // Capturar o arquivo de imagem

        var formData = new FormData();
        formData.append("title", titulo);
        formData.append("text", descricao);
        formData.append("urgency", urgencia);
        formData.append("org_id", orgaoId);
        formData.append("tip_id", tipoId);
        formData.append("usu_id", seu_usuario_id); // Substitua 'seu_usuario_id' pelo ID do usuário atual
        formData.append("image", imagem);

        fetch('http://localhost:8080/apis/citizen/add-complaint', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                // Manipular a resposta do servidor, por exemplo, exibir uma mensagem de sucesso
                console.log(data);
                alert("Denúncia enviada com sucesso!");
            })
            .catch(error => {
                // Manipular erros, por exemplo, exibir uma mensagem de erro
                console.error('Erro ao enviar denúncia:', error);
                alert("Erro ao enviar denúncia. Por favor, tente novamente.");
            });
    }

    // Adicionar um listener para o evento de clique no botão de enviar denúncia
    document.getElementById("enviarDenunciaBtn").addEventListener("click", enviarDenuncia);
});