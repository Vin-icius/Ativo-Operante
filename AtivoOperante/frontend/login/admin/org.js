document.addEventListener("DOMContentLoaded", function() {
    // Carregar órgãos competentes existentes ao carregar a página
    carregarOrgaosCompetentes();

    // Adicionar evento de submit para o formulário de adicionar órgão
    const formOrgao = document.getElementById("formOrgao");
    formOrgao.addEventListener("submit", function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário
        adicionarOrgaoCompetente();
    });
});

// Função para exibir ou ocultar o formulário de inserção de órgão
function exibirFormulario() {
    const formulario = document.getElementById("formOrgao");
    formulario.style.display = formulario.style.display === "none" ? "block" : "none";
}

// Função para carregar todos os órgãos competentes
function carregarOrgaosCompetentes() {
    const URL = "http://localhost:8080/apis/admin/get-all-agencies";
    const tabelaOrgaos = document.getElementById("tabelaOrgaos");

    fetch(URL)
        .then(response => response.json())
        .then(orgaos => {
            tabelaOrgaos.innerHTML = ""; // Limpa a tabela antes de adicionar os novos órgãos
            orgaos.forEach(orgao => {
                const row = document.createElement("div");
                row.classList.add("orgao-row");
                row.innerHTML = `
                    <span>${orgao.name}</span>
                    <button onclick="editarOrgaoCompetente(${orgao.id})">Editar</button>
                    <button onclick="excluirOrgaoCompetente(${orgao.id})">Excluir</button>
                `;
                tabelaOrgaos.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar órgãos competentes:", error);
        });
}

// Função para adicionar um novo órgão competente
function adicionarOrgaoCompetente() {
    const URL = "http://localhost:8080/apis/admin/add-agency";
    const orgaoInput = document.getElementById("orgao").value;
    const formData = { name: orgaoInput };

    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                console.log("Órgão competente adicionado com sucesso!");
                carregarOrgaosCompetentes(); // Recarregar lista após adicionar novo órgão
                document.getElementById("orgao").value = ''; // Limpar o campo de entrada
            } else {
                console.error("Falha ao adicionar órgão competente.");
            }
        })
        .catch(error => {
            console.error("Erro ao adicionar órgão competente:", error);
        });
}
/*
// Função para editar um órgão competente
function editarOrgaoCompetente(orgaoId) {
    const novoNome = prompt("Digite o novo nome para o órgão competente:");
    if (novoNome !== null) {
        const URL = `http://localhost:8080/apis/admin/edit-agency`;
        const formData = { id: orgaoId, name: novoNome };

        fetch(URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (response.ok) {
                    console.log("Órgão competente editado com sucesso!");
                    carregarOrgaosCompetentes(); // Recarregar lista após editar órgão
                } else {
                    console.error("Falha ao editar órgão competente.");
                }
            })
            .catch(error => {
                console.error("Erro ao editar órgão competente:", error);
            });
    }
}
*/
// Função para excluir um órgão competente
function excluirOrgaoCompetente(orgaoId) {
    const URL = `http://localhost:8080/apis/admin/delete-agency?org_id=${orgaoId}`;

    fetch(URL, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                console.log("Órgão competente excluído com sucesso!");
                carregarOrgaosCompetentes(); // Recarregar lista após excluir órgão
            } else {
                console.error("Falha ao excluir órgão competente.");
            }
        })
        .catch(error => {
            console.error("Erro ao excluir órgão competente:", error);
        });
}
