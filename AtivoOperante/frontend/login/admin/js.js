// Aqui você pode adicionar um script para enviar o feedback para cada denúncia
document.querySelectorAll('button').forEach(button => {
    button.addEventListener('click', () => {
      const feedback = button.parentNode.previousElementSibling.querySelector('input').value;
      // Aqui você pode implementar o código para enviar o feedback para o servidor
      alert(`Feedback enviado: ${feedback}`);
    });
  });

  function cadastrarOrgaoCompetente(){}
  function cadastrarTipoProblema(){}

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