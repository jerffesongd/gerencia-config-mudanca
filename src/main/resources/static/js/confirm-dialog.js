function confirm(opcoes,event){
	
    event.preventDefault();
    
    var url = event && (('string' === typeof event && event) || (event.currentTarget && event.currentTarget.attributes['href'].value));

    swal({
            title: opcoes.title ? opcoes.title: "Você deseja realmente efetuar esta operação?",
            text: opcoes.text ? opcoes.text: "",
            type: opcoes.type ? opcoes.type: "warning",
            showCancelButton: true,
            buttons: ["Não", "Sim"],
        }).then((isConfirm) => { 
        
            if (isConfirm) {
                if(typeof(opcoes.confirm)=="function"){
                    opcoes.confirm.call();
                }else if (opcoes.post) {
                    var form = $('<form method="get" class="hide" action="' + url + '"></form>');
                    $("body").append(form);
                    form.submit();
                } else {
                    console.log(url);
                    window.location = url;
                }
            }
        }
     );
        
}
