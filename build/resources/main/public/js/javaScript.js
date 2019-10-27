$(document).ready(function(){

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