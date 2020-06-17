
/**
 * Comprueba que los input de un formulario se han informado
 */
function validateMandatoryFields(form) {
	var elements = form.elements;
	
	// elementos del formulario
	for(i = 0; i < elements.length; i++) {
		
		//filtramos los required
		if(elemets[i].required && elements[i].value.trim() == '') {
			alert(elemets[i].name + " no se ha informado");
			return false;
		}
	}
	return true;
}

/**
 * Hace submit en un formulario pasado por parametro
 */
function submitForm(form, task, state, id) {
	var taskVar = form.elements['TASK'];

	taskVar.value = task;
	
	if(state != null) {
		var stateVar = form.elements['STATEID'];
		stateVar.value = state;
		
		var idVar = form.elements['ORDERID'];
		idVar.value = id;
	}
	
	form.submit();
}
