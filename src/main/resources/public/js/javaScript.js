$(document).ready(function(){
    var data;

    tags()
    submitArticle()

    $( "#editUser" ).click(function() {
        $('#modalListUser').modal('hide')
        $('#titleModalRegister-update').empty()
        $('#titleModalRegister-update').text('Editar Usuario')
        $('.input-full-name-modal-register-update').attr('readonly', true);
        $('#register-update').attr('action','/editUser')

      });

      $( "#regiter-user-bar" ).click(function() {
          checkBox()
        $('#titleModalRegister-update').empty()
        $('#titleModalRegister-update').text('Crear Usuario')
        $('.input-full-name-modal-register-update').attr('readonly', false);
        $('#register-update').attr('action','/registerUser');
        $('#materialUncheckedAuthor').attr('value',false);
        $('#materialUncheckedAdmin').attr('value',false);
      });

      $('#deleteModalConfirm').click(function(){
        $('#modalListUser').modal('hide')

      })

})
function checkBox() {
    $('#materialUncheckedAuthor').click(function () {
        if ($(this).is(":checked")){
            $('#materialUncheckedAuthor').attr('value',true);
        }else if ($(this).is(":not(:checked)")){
            $('#materialUncheckedAuthor').attr('value',false);
        }
    })
    $('#materialUncheckedAdmin').click(function () {
        if ($(this).is(":checked")){
            $('#materialUncheckedAdmin').attr('value',true);
        }else if ($(this).is(":not(:checked)")){
            $('#materialUncheckedAdmin').attr('value',false);
        }
    })

}

function tags(){
    var k = []
    $('input.tags-input').keypress(function(e){
        var key = e.which
        if(key === 13){
            var flag = 0;
            var value = $('input.tags-input').val()
            $(this).val('')
            for(var i = 0;i< k.length;i++){
                if( k[i] == value ){
                    flag = 1;
                }
            }
            if(flag === 0){
                k.push(value)
                var span = document.createElement("span")
                span.className = "badge badge-pill badge-info ml-1 "
                span.innerHTML = k[k.length-1]
                $('div.tags').append(span)
                console.log(k)
                $('div.tags span').click(function(e) {
                    var txt = $(e.target).text();
                    for(var i = 0;i<k.length;i++){
                        if(k[i] == txt){
                            $(e.target).remove()
                            k.splice(i,1);
                            data = JSON.stringify(k);
                            console.log("Data" + data)

                        }
                    }

                })


            }
             data = JSON.stringify(k);

        }


    })
    return k;
}
function submitArticle() {
    $('#postArticle').click(function () {
        var title = $('input.name-article').val()
        var article = $('textarea.exampleFormControlTextarea5').val()

        var x = "[uno,dos]"
        $.get("/registerArticle",{title:title,article:article,json:data},
            function () {


            })

    })

}
