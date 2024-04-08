/**
 * Validação de formulario
 * @author Marcio Junior
//criar uma função
function validar() {

	//alert para testar a conexão do JS com o HTML
	//alert("test")

	//declarar variavel local(dentro da função)
	let nome = frmcontato.nome.value
	/*			^puxar do form
	*						^puxar o nome do td
	*							^oque vai puxar
	*/
	let fone = frmcontato.fone.value

	if(nome === "" && fone === ""){
		alert("Preencha o campo nome e o campo fone")
		frmContato.nome.focus()
		frmcontato.fone.focus()
		return false
	}else if (nome === "") {
		alert("Preencha o campo nome")
		frmContato.nome.focus()
		return false
	}else if(fone === ""){
		alert("Preencher o campo fone")
		frmcontato.fone.focus()
		return false
	}else{
		document.forms["frmcontatos"].subimit()
 }