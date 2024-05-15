// Aqui você pode adicionar um script para enviar o feedback para cada denúncia
document.querySelectorAll('button').forEach(button => {
    button.addEventListener('click', () => {
      const feedback = button.parentNode.previousElementSibling.querySelector('input').value;
      // Aqui você pode implementar o código para enviar o feedback para o servidor
      alert(`Feedback enviado: ${feedback}`);
    });
  });


  //////////////////////////////////////////////////////////////////////
  function cadastrarOrgaoCompetente(){}
  function cadastrarTipoProblema(){
    const URL = "http://localhost:8080/apis/admin/add-type";
    var fdados = document.getElementById("formTipo");
    
    fetch(URL, {
        method: 'POST', body: new FormData(fdados),
    })
        .then(resp=> {
            return resp.text();
        })
        .then(text=> {
            console.log("Deu certo!");
        }).catch(error=> {
            console.error(error);
        });
  }

  ////////////////////////////////////////////////////////////////////////

  function carregaDenuncias()
{

    const URL="http://localhost:8080/apis/admin/get-all-complaints";
    const tag = document.getElementById("tabelaDen");
    //var denForm = document.getElementById("denForm");  //esse é para pesquisar uma denuncia especifica

    fetch(URL, {
        method: 'GET', body: new FormData(denForm)
    })
        .then(resp=>{
            return resp.json()
            .then(json=>{

                let list="";
                for (let den of json)
                {
                    console.log(den);
                    list+=`
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Denúncia</th>
                            <th>Status</th>
                            <th>Feedback</th>
                            <th>Ações</th>
                        </tr>
                        <tr>
                            <td>${den}</td>
                            <td>${den}</td>
                            <td>${den}</td>
                            <td><input type="text"></td>
                            <td>
                                <button>Deletar</button>
                                <button>Aprovar</button>
                            </td>
                        </tr>
                    </table>

                    <table>
                        <tr>
                            <td style="border-radius: 10px 10px / 12px;">
                                <div style="text-align: center">
                                    <span style="display: block; margin-bottom: 10px;" ><b>${music}</b></span>
                                    <audio controls>
                                        <source src="${audioPath+music}" type="audio/mpeg">
                                    </audio>
                                </div>
                            </td>
                        </tr>
                    </table>`
                }

                tag.innerHTML=list;
            })
        })
        .catch(Err=>{
            console.error="Erro"+Err;
        })
}

  ////////////////////////////////////////////////////////////////////////

  let exibindoFormulario1 = false;
  let exibindoFormulario2 = false;

  function exibirFormulario1() {
      if (exibindoFormulario1) {
          document.getElementById("formulario1").style.display = "none";
          exibindoFormulario1 = false;
      } else {
          document.getElementById("formulario1").style.display = "block";
          exibindoFormulario1 = true;
      }
  }
  function exibirFormulario2() {
    if (exibindoFormulario2) {
        document.getElementById("formulario2").style.display = "none";
        exibindoFormulario2 = false;
    } else {
        document.getElementById("formulario2").style.display = "block";
        exibindoFormulario2 = true;
    }
}