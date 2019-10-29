/*
$(document).ready(function () {


})
function listUser() {
    $('#list-user').click(function () {
        $.get("/listUser",function (data) {
            var x = JSON.parse(data)
            var table = document.getElementById("listUsers");
            $('tbody').remove();
            for(var i = 0;i<x.users.length;i++){


                var row = table.insertRow(i);
                var username = row.insertCell(0);

                var name = row.insertCell(1);
                var author = row.insertCell(2);
                var admin = row.insertCell(3);
                var update = row.insertCell(4);
                var trash = row.insertCell(5);

                username.innerHTML = x.users[i].username;
                name.innerHTML = x.users[i].name;
                author.innerHTML = x.users[i].author;
                admin.innerHTML = x.users[i].admin;
                update.innerHTML = "<a class=\"editUser\" id=\"editUser\" data-toggle=\"modal\" data-target=\"#RegisterUserModal\"><i class=\"fas fa-edit\"></i></a>"
                trash.innerHTML = "<a  id=\"deleteModalConfirm\" data-toggle=\"modal\" data-target=\"#modalConfirmDelete\"><i class=\"fas fa-times\"></i></a>"



            }
        })

    })

}
function editUser() {
    $( "#editUser" ).click(function() {
        $('#modalListUser').modal('hide')
        $('#titleModalRegister-update').empty()
        $('#titleModalRegister-update').text('Editar Usuario')
        $('.input-full-name-modal-register-update').attr('readonly', true);
        $('#register-update').attr('action','/editUser')

    });

}
*/