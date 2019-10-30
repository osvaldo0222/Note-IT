$(document).on('click','#editUser',function () {

    console.log("PEPE")
    var curRow = $(this).closest('tr');
    var username = curRow.find('td:eq(0)').text();

    var name = curRow.find('td:eq(1)').text();

    var author = curRow.find('td:eq(2)').text();

    var admin = curRow.find('td:eq(3)').text();



    $('#modalListUser').modal('hide')
    $('#titleModalRegister-update').empty()
    $('#titleModalRegister-update').text('Editar Usuario')
    $('.input-full-name-modal-register-update').attr('readonly', true);
    $('.input-full-name-modal-register-update').attr('value', username);
    $('label.input-full-name-modal-register-update').attr('class','active')
    $('#register-update').attr('action','/editUser')
    $('.name-input').attr('value',name)
    $('label.name-input').attr('class','active')
    if(author == "true"){
        $('#materialUncheckedAuthor').attr('value',true);
        $('#materialUncheckedAuthor').prop('checked',true);
    }else{
        $('#materialUncheckedAuthor').attr('value',false);
        $('#materialUncheckedAuthor').prop('checked',false);

    }
    if(admin == "true"){
        $('#materialUncheckedAdmin').attr('value',true);
        $('#materialUncheckedAdmin').prop('checked',true);
    }else{
        $('#materialUncheckedAdmin').attr('value',false);
        $('#materialUncheckedAdmin').prop('checked',false);

    }

})
listUser()
submitArticle()
$(document).ready(function(){
    var data;

    tags()


    var array = []



      $( "#regiter-user-bar" ).click(function() {
          checkBox()
        $('#titleModalRegister-update').empty()
        $('#titleModalRegister-update').text('Crear Usuario')
        $('.input-full-name-modal-register-update').attr('readonly', false);
        $('#register-update').attr('action','/registerUser');
        $('#materialUncheckedAuthor').attr('value',false);
        $('#materialUncheckedAdmin').attr('value',false);
        $('#materialUncheckedAdmin').prop('checked',false);
        $('#materialUncheckedAuthor').prop('checked',false);
        $('.name-input').attr('value',"");
        $('.input-full-name-modal-register-update').attr('value', "");

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
        $.post("/registerArticle",{title:title,article:article,json:data},
            function () {
            })
        alert("q")
        history.go(0);
    })

}
function listUser() {
        $('#list-user').click(function () {
            $.get("/listUser",function (data) {
                var x = JSON.parse(data)
                var table = document.getElementById("listUsers");
                $('tbody').remove();
                var header = table.insertRow(0);
                header.insertCell(0).innerHTML = "<strong>Usuario</strong>";
                header.insertCell(1).innerHTML = "<strong>Nombre</strong>";
                header.insertCell(2).innerHTML = "<strong>Autor</strong>";
                header.insertCell(3).innerHTML = "<strong>Administrador</strong>";
                header.insertCell(4).innerHTML = "<strong>Editar</strong>";
                header.insertCell(5).innerHTML = "<strong>Eliminar</strong>";
                for(var i = 0;i<x.users.length;i++){
                    var row = table.insertRow(i + 1);
                    var username = row.insertCell(0);
                    var name = row.insertCell(1);
                    var author = row.insertCell(2);
                    var admin = row.insertCell(3);
                    var update = row.insertCell(4);
                    var trash = row.insertCell(5);
                    username.innerHTML = x.users[i].username;
                    name.innerHTML = x.users[i].name;
                    author.innerHTML = x.users[i].author;
                    admin.innerHTML = x.users[i].administrator;
                    update.innerHTML = "<a class=\"editUser\" id=\"editUser\" data-toggle=\"modal\" data-target=\"#RegisterUserModal\"><i class=\"fas fa-edit\"></i></a>"
                    trash.innerHTML = "<a  id=\"deleteModalConfirm\" data-toggle=\"modal\" data-target=\"#modalConfirmDelete\"><i class=\"fas fa-times\"></i></a>"
                }
            })
        })
}

