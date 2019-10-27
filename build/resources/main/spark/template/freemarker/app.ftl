<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Material Design Bootstrap</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="/css/mdb.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->

  <link href="css/style.css" rel="stylesheet">
</head>

<body>

  <!-- Start your project here-->
   


  <div class="container-fluid gedf-wrapper">
      <div class="row">
          <div class="col-md-3 ">
              <div class=" sticky-top" id="right-column">
                <div class="row">
                  <div class="col-md-12">
                      <div>
                          <img src="https://img.icons8.com/ultraviolet/60/000000/my-topic.png">  <span class="ml-1 blog">Blog-ISC-415</span>                 
                           </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-11">
                   
                      <div class="list-group list-group-flush ">
                           
                          <a href="#" class="list-group-item   waves-effect" id="regiter-user-bar" data-toggle="modal" data-target="#RegisterUserModal">
                            <i class="fas fa-user-plus mr-3"></i>Registrar Usuario</a>
                          <a href="#" class="list-group-item   waves-effect" data-toggle="modal" data-target="#modalListUser">
                            <i class="fas fa-list mr-3"></i>Listar Usuario</a>
                          <a href="#" class="list-group-item   waves-effect">
                            <i class="fas fa-newspaper mr-3"></i>Listar Articulos</a>
                          
                        </div>

                  </div>
                </div>
                 
                 
                   
                 
              </div>
          </div>
          <div class="col-md-6 gedf-main">

              <!--- \\\\\\\Post-->
              <div class="card gedf-card">
                  <div class="card-header">
                      <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                          <li class="nav-item">
                              <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Crear Publicación</a>
                          </li>
                           
                      </ul>
                  </div>
                  <div class="card-body">
                      <div class="tab-content" id="myTabContent">

                          <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                              <div class="form-group green-border-focus">
                                <!--Input field for post-->
                                <div class="row">
                                  <div class="col">
                                      <div class="md-form form-sm">
                                          <input type="text" id="inputSMEx" class="pubTitle form-control form-control-sm">
                                          <label for="inputSMEx">Título</label>
                                        </div>
                                  </div>
                                  <div class="col">
                                      <div class="md-form form-sm">
                                          <input type="text" id="inputSMEx" class="pubPublication form-control form-control-sm">
                                          <label for="inputSMEx">Etiqueras</label>
                                        </div>
                                  </div>
                                </div>
                                   
                                  <textarea class="form-control" id="exampleFormControlTextarea5" rows="3"></textarea>
                                </div>
                                     <!--Form for article to submit-->
                          </div>
                      </div>
                      <div class="btn-toolbar justify-content-between float-right">
                          <div class="btn-group">
                              <button type="submit" class="btn btn-primary">Publicar</button>
                          </div>
                          
                      </div>
                  </div>
                  <!--Input field for post-->
              </div>
              <!-- Post /////-->

              <!--- \\\\\\\Post model-->
              <div class="card gedf-card">
                  <div class="card-header">
                      <div class="d-flex justify-content-between align-items-center">
                          <div class="d-flex justify-content-between align-items-center">
                              <div class="mr-2">
                                  <img class="rounded-circle" width="45" src="https://picsum.photos/50/50" alt="">
                              </div>
                              <div class="ml-2">
                                  <div class="h5 m-0">Username</div>
                                  <div class="h7 text-muted">Username name</div>
                              </div>
                          </div>
                          <div>
                              <div class="dropdown">
                                  <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                      <i class="fa fa-ellipsis-h"></i>
                                  </button>
                                  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                      <div class="h6 dropdown-header">Modificaciones</div>
                                      <a class="dropdown-item" href="#">Editar artículo</a>
                                      <a class="dropdown-item" href="#">Eliminar artículo</a>
                                      
                                  </div>
                              </div>
                          </div>
                      </div>

                  </div>
                  <div class="card-body">
                      <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>Publication date</div>
                      <a class="card-link" href="#">
                          <h5 class="card-title">Titulo de articulo</h5>
                      </a>

                      <p class="card-text">
                          Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo recusandae nulla rem eos ipsa praesentium esse magnam nemo dolor
                          sequi fuga quia quaerat cum, obcaecati hic, molestias minima iste voluptates.
                      </p>
                  </div>
                  <div class="card-footer">
                      <a href="#" class="card-link"><i class="fa fa-gittip"></i> Me gusta</a>
                      <a href="#" class="card-link"><i class="fa fa-comment"></i> Comentar</a>
                      
                  </div>
              </div>
              <!-- Post model /////-->

 


              
              <!-- Post /////-->
                <!--- \\\\\\\Post model-->
                <div class="card gedf-card">
                    <div class="card-header">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="mr-2">
                                    <img class="rounded-circle" width="45" src="https://picsum.photos/50/50" alt="">
                                </div>
                                <div class="ml-2">
                                    <div class="h5 m-0">Username</div>
                                    <div class="h7 text-muted">Username name</div>
                                </div>
                            </div>
                            <div>
                                <div class="dropdown">
                                    <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-ellipsis-h"></i>
                                    </button>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                        <div class="h6 dropdown-header">Modificaciones</div>
                                        <a class="dropdown-item" href="#">Editar artículo</a>
                                        <a class="dropdown-item" href="#">Eliminar artículo</a>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
  
                    </div>
                    <div class="card-body">
                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>Publication date</div>
                        <a class="card-link" href="#">
                            <h5 class="card-title">Titulo de articulo</h5>
                        </a>
  
                        <p class="card-text">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo recusandae nulla rem eos ipsa praesentium esse magnam nemo dolor
                            sequi fuga quia quaerat cum, obcaecati hic, molestias minima iste voluptates.
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="card-link"><i class="fa fa-gittip"></i> Me gusta</a>
                        <a href="#" class="card-link"><i class="fa fa-comment"></i> Comentar</a>
                        
                    </div>
                </div>
                <!-- Post model /////-->



          </div>
          <div class="col-md-3 ">
              <div class="card gedf-card position-fixed" id="right-column">
                  <ul class="list-group">
                      <li class="list-group-item d-flex justify-content-between align-items-center list-group-item-action active">
                          ->Como se filtrar las etiquetas
                       
                        </li>
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        Cras justo odio
                        <span class="badge badge-primary badge-pill">14</span>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        Dapibus ac facilisis in
                        <span class="badge badge-primary badge-pill">2</span>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        Morbi leo risus
                        <span class="badge badge-primary badge-pill">1</span>
                      </li>
                    </ul>
              </div>
             
          </div>
      </div>
  </div>



    <!-- Modal: modalCart -->
<div class="modal fade" id="modalListUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
aria-hidden="true">
<div class="modal-dialog modal-lg" role="document">
  <div class="modal-content">
    <!--Header-->
    <div class="modal-header">
      <h4 class="modal-title w-100" id="myModalLabel">Listar usuario</h4>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">×</span>
      </button>
    </div>
    <!--Body-->
    <div class="modal-body">

      <table class="table table-hover">
        <thead>
          <tr>
            <th>Username</th>
            <th>Nombre</th>
            <th>Password</th>
            <th>Autor</th>
            <th>Administrador</th>
            <th>Modificar</th>
            <th>Eliminar</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>garco</th>
            <td>edgar garcia </td>
            
            <td>Password</td>
            <th>Checked</th>
            <th>checked</th>
            <td class="editUser" id="editUser" data-toggle="modal" data-target="#RegisterUserModal"><a><i class="fas fa-edit"></i></a></td>
            <td><a  id="deleteModalConfirm" data-toggle="modal" data-target="#modalConfirmDelete"><i class="fas fa-times"></i></a></td>
          </tr>
          
        </tbody>
      </table>

    </div>
    <!--Footer-->
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancelar</button>
      <button class="btn btn-primary" type="submit">Salvar</button>
    </div>
  </div>
</div>
</div>
<!-- Modal: modalCart -->






<!--Register user modal-->
<!-- Central Modal Small -->
<form action="" method="POST" class="register-update" id="register-update">
<div class="modal fade" id="RegisterUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true">

  <!-- Change class .modal-sm to change the size of the modal -->
  <div class="modal-dialog modal-md" role="document">


    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title w-100" id="titleModalRegister-update">Registrar Usuario</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="md-form">
              <input type="text" id="inputMDEx" class="form-control input-full-name-modal-register-update" name="username">
              <label for="inputMDEx">Nombre de usuario</label>
            </div>
          <div class="md-form">
              <input type="text" id="inputMDEx" class="form-control" name="name">
              <label for="inputMDEx">Nombre completo</label>
            </div>
            <div class="md-form">
                <input type="text" id="inputMDEx" class="form-control" name="password">
                <label for="inputMDEx">Password</label>
              </div>
               
                <!-- Material unchecked -->
                <div class="d-flex p-2 col-example">
                 
                <div class="form-check ml-auto mr-2">
                    <input type="checkbox" class="form-check-input" id="materialUncheckedAuthor" name="materialUncheckedAuthor">
                    <label class="form-check-label" for="materialUnchecked">Autor</label>
                </div>
                <div class="form-check mr-auto">
                    <input type="checkbox" class="form-check-input" id="materialUncheckedAdmin" name="materialUncheckedAdmin">
                    <label class="form-check-label" for="materialUnchecked">Administrador</label>
                </div>
              
              </div>

            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn- btn-sm" data-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-primary btn-sm">Salvar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!-- Central Modal Small -->

<!--Modal confirm delete-->
<!--Modal: modalConfirmDelete-->
<div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog modal-sm modal-notify modal-danger" role="document">
    <!--Content-->
    <div class="modal-content text-center">
      <!--Header-->
      <div class="modal-header d-flex justify-content-center">
        <p class="heading">Eliminar usuario</p>
      </div>

      <!--Body-->
      <div class="modal-body">

        <i class="fas fa-times fa-4x animated rotateIn"></i>
        <div class="row">
          <div class="col"> 
            <span>¿Seguro de eliminar el usuario xxxx?</span>
          </div>
        </div>
       

      </div>

      <!--Footer-->
      <div class="modal-footer flex-center">
        <a href="" class="btn  btn-outline-danger" type="submit">Si!</a>
        <a type="button" class="btn  btn-danger waves-effect" data-dismiss="modal">Cancelar</a>
      </div>
    </div>
    <!--/.Content-->
  </div>
</div>
<!--Modal: modalConfirmDelete-->


  <!-- Start your project here-->

  <!-- SCRIPTS -->
  <!-- JQuery -->
  
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="js/mdb.min.js"></script>
  <script type="text/javascript" src="js/javaScript.js"></script>
</body>

</html>