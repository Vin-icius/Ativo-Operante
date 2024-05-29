document.addEventListener("DOMContentLoaded", function() {
    // Carregar tipos de problema existentes ao carregar a página
    carregarTiposProblema();

    // Adicionar evento de submit para o formulário de adicionar tipo
    const formTipo = document.getElementById("formTipo");
    formTipo.addEventListener("submit", function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário
        adicionarTipoProblema();
    });
});

// Função para carregar todos os tipos de problema
function carregarTiposProblema() {
    const URL = "http://localhost:8080/apis/admin/get-all-types";
    const listaTipos = document.getElementById("listaTipos");

    fetch(URL)
        .then(response => response.json())
        .then(tipos => {
            listaTipos.innerHTML = ""; // Limpa a lista antes de adicionar os novos tipos
            tipos.forEach(tipo => {
                const listItem = document.createElement("li");
                listItem.innerHTML = `
                    <span>${tipo.name}</span>
                    <button onclick="editarTipoProblema(${tipo.id})">Editar</button>
                    <button onclick="excluirTipoProblema(${tipo.id})">Excluir</button>
                `;
                listaTipos.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar tipos de problema:", error);
        });
}

// Função para adicionar um novo tipo de problema
function adicionarTipoProblema() {
    const URL = "http://localhost:8080/apis/admin/add-type";
    const problemaInput = document.getElementById("problema").value;
    const formData = { name: problemaInput }; // Ajuste para enviar 'name' em vez de 'problema'

    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                console.log("Tipo de problema adicionado com sucesso!");
                carregarTiposProblema(); // Recarregar lista após adicionar novo tipo
                formTipo.reset(); // Limpar o formulário
            } else {
                console.error("Falha ao adicionar tipo de problema.");
            }
        })
        .catch(error => {
            console.error("Erro ao adicionar tipo de problema:", error);
        });
}

// Função para editar um tipo de problema
function editarTipoProblema(tipoId) {
    const novoNome = prompt("Digite o novo nome para o tipo de problema:");
    if (novoNome !== null) {
        const URL = `http://localhost:8080/apis/admin/edit-type`;
        const formData = { id: tipoId, name: novoNome };

        fetch(URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (response.ok) {
                    console.log("Tipo de problema editado com sucesso!");
                    carregarTiposProblema(); // Recarregar lista após editar tipo
                } else {
                    console.error("Falha ao editar tipo de problema.");
                }
            })
            .catch(error => {
                console.error("Erro ao editar tipo de problema:", error);
            });
    }
}

// Função para excluir um tipo de problema
function excluirTipoProblema(tipoId) {
    const URL = `http://localhost:8080/apis/admin/delete-type?tip_id=${tipoId}`;

    fetch(URL, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                console.log("Tipo de problema excluído com sucesso!");
                carregarTiposProblema(); // Recarregar lista após excluir tipo
            } else {
                console.error("Falha ao excluir tipo de problema.");
            }
        })
        .catch(error => {
            console.error("Erro ao excluir tipo de problema:", error);
        });
}