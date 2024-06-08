document.addEventListener("DOMContentLoaded", function() {
    // Adicionar eventos de clique para os botões das abas
    document.getElementById("btnOrgaos").addEventListener("click", function() {
        mostrarAbaOrgaos();
    });

    document.getElementById("btnTipos").addEventListener("click", function() {
        mostrarAbaTipos();
    });

    document.getElementById("btnDenuncias").addEventListener("click", function() {
        mostrarAbaDenuncias();
    });

    // Adicionar eventos de submit para os formulários
    const formOrgao = document.getElementById("formOrgao");
    formOrgao.addEventListener("submit", function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário
        adicionarOrgaoCompetente();
    });

    const formTipo = document.getElementById("formTipo");
    formTipo.addEventListener("submit", function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário
        adicionarTipoProblema();
    });
});

function mostrarAbaOrgaos() {
    document.getElementById("formularioOrgao").style.display = 'block';
    document.getElementById("formularioTipo").style.display = 'none';
    document.getElementById("formularioDenuncias").style.display = 'none';
    carregarOrgaosCompetentes(); // Carregar dados ao clicar na aba
}

function mostrarAbaTipos() {
    document.getElementById("formularioOrgao").style.display = 'none';
    document.getElementById("formularioTipo").style.display = 'block';
    document.getElementById("formularioDenuncias").style.display = 'none';
    carregarTiposProblema(); // Carregar dados ao clicar na aba
}

function mostrarAbaDenuncias() {
    document.getElementById("formularioOrgao").style.display = 'none';
    document.getElementById("formularioTipo").style.display = 'none';
    document.getElementById("formularioDenuncias").style.display = 'block';
    carregarDenuncias(); // Carregar dados ao clicar na aba
}

function carregarDenuncias() {
    const URL = "http://localhost:8080/apis/admin/get-all-complaints";
    const tabelaDenuncias = document.getElementById("tabelaDenuncias");

    fetch(URL)
        .then(response => response.json())
        .then(denuncias => {
            tabelaDenuncias.innerHTML = ""; // Limpa a tabela antes de adicionar as novas denúncias
            denuncias.forEach(denuncia => {
                const agencyName = denuncia.agency ? denuncia.agency.name : "Não especificado";
                const typeName = denuncia.type ? denuncia.type.name : "Não especificado";
                const userName = denuncia.user ? denuncia.user.name : "Não especificado";
                const userEmail = denuncia.user ? denuncia.user.email : "Não especificado";

                const row = document.createElement("div");
                row.classList.add("denuncia-row");
                row.innerHTML = `
                    <span><strong>Título:</strong> ${denuncia.title}</span>
                    <span><strong>Texto:</strong> ${denuncia.text}</span>
                    <span><strong>Urgência:</strong> ${denuncia.urgency}</span>
                    <span><strong>Órgão:</strong> ${agencyName}</span>
                    <span><strong>Data:</strong> ${denuncia.data}</span>
                    <span><strong>Tipo:</strong> ${typeName}</span>
                    <span><strong>Usuário:</strong> ${userName}</span>
                    <span><strong>Email do Usuário:</strong> ${userEmail}</span>
                    <button onclick="excluirDenuncia(${denuncia.id})">Excluir</button>
                `;
                tabelaDenuncias.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar denúncias:", error);
        });
}

function excluirDenuncia(denunciaId) {
    const URL = `http://localhost:8080/apis/admin/delete-complaint?den_id=${denunciaId}`;

    fetch(URL, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                console.log("Denúncia excluída com sucesso!");
                carregarDenuncias(); // Recarregar lista após excluir denúncia
            } else {
                console.error("Falha ao excluir denúncia.");
            }
        })
        .catch(error => {
            console.error("Erro ao excluir denúncia:", error);
        });
}

// Funções para órgãos competentes (já existentes)
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
                    <button onclick="editarOrgao(${orgao.id})">Editar</button>
                    <button onclick="excluirOrgaoCompetente(${orgao.id})">Excluir</button>
                `;
                tabelaOrgaos.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar órgãos competentes:", error);
        });
}

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

function editarOrgao(orgaoId) {
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

// Funções para tipos de problema (já existentes)
function carregarTiposProblema() {
    const URL = "http://localhost:8080/apis/admin/get-all-types";
    const listaTipos = document.getElementById("tiposProblema");

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
                document.getElementById("formTipo").reset(); // Limpar o formulário
            } else {
                console.error("Falha ao adicionar tipo de problema.");
            }
        })
        .catch(error => {
            console.error("Erro ao adicionar tipo de problema:", error);
        });
}

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