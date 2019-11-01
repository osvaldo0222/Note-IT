<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Note-IT</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->

    <link href="/css/style.css" rel="stylesheet">
</head>

<body>
<!-- Start your project here-->
<#if !(user??)>
    <nav class="navbar navbar-dark  info-color fixed-top ">
        <a class="navbar-brand" href="/"><img src="https://img.icons8.com/ultraviolet/60/000000/my-topic.png" width="40" height="40">  <span class="ml-1 blog">Note-IT</span></a>
        <form class="form-inline my-1">
            <div class="md-form form-sm my-0 ml-auto">
                <input class="form-control form-control-sm mr-sm-2 mb-0" type="text" placeholder="Search" aria-label="Search">
            </div>
            <button class="btn btn-outline-white btn-sm my-0" type="submit">Buscar </button>
        </form>
        <a href="/login"><button class="btn btn-outline-white btn-sm my-0" type="submit">Login </button></a>
    </nav>
    <style>
        #firstRow {
            margin-top: 100px;
        }
    </style>
</#if>


<div class="con114tainer-fluid gedf-wrapper" id="firstRow">

    <div class="row">


        <div class="col-md-3 " id="">
            <div class=" sticky-top" id="right-column">
                <#if user??>
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                                <img src="https://img.icons8.com/ultraviolet/60/000000/my-topic.png">  <span class="ml-1 blog">Note-IT</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                           

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-11">

                            <div class="list-group list-group-flush ">
                                <a href="/" class="list-group-item   waves-effect" id="regiter-user-bar"  >
                                    <i class="fas fa-user-plus mr-3"></i>Inicio</a>

                                <#if user.administrator == true>
                                    <a href="#" class="list-group-item   waves-effect" id="regiter-user-bar" data-toggle="modal" data-target="#RegisterUserModal">
                                        <i class="fas fa-user-plus mr-3"></i>Registrar Usuario</a>
                                    <a href="#" class="list-group-item   waves-effect" data-toggle="modal" data-target="#modalListUser" id="list-user">
                                        <i class="fas fa-list mr-3"></i>Listar Usuario</a>
                                </#if>
                                <#if user.administrator == true || user.author == true>
                                    <a href="#" class="list-group-item   waves-effect">
                                        <i class="fas fa-newspaper mr-3"></i>Listar Articulos</a>
                                </#if>
                                <a href="/closeSession" class="list-group-item   waves-effect">
                                    <i class="fa fa-sign-out-alt mr-3"></i>Log-out</a>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>




        <div class="col-md-6 gedf-main">

            <!--- \\\\\\\Post-->

            <!-- Post /////-->

            <div class="row">
                <div class="col-12">
                    <!--- \\\\\\\Post model-->
                    <#if article??>


                            <div class="card gedf-card">
                                <div class="card-header">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="mr-2">
                                                <img class="rounded-circle" width="45" src="https://picsum.photos/50/50" alt="">
                                            </div>
                                            <div class="ml-2">
                                                <h5 class="card-title">${article.title}</h5>
                                                <div class="h7 m-0">Por: ${article.author.name}  <div class="text-muted h7 mb-2 float-right"> <i class="fa fa-clock-o"></i>  ${article.date}</div></div>
                                                <div class="h7 text-muted"><#--${article.author.username}--></div>
                                            </div>
                                        </div>
                                        <div>
                                            <#if user?? && (user.author == true || user.administrator == true)>
                                            <div class="dropdown">
                                                <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fa fa-ellipsis-h"></i>
                                                </button>
                                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                                    <div class="h6 dropdown-header">Modificaciones</div>
                                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#updateArticle" id="update-article" value="${article.id}">Editar artículo</a>
                                                    <a class="dropdown-item" id="deleteModalConfirmDeleteArticle" data-toggle="modal" data-target="#modalConfirmDelete" >Eliminar artículo</a>

                                                </div>
                                            </div>
                                            </#if>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">

                                        </div>
                                    </div>

                                </div>
                                <div class="card-body">

                                    <div class="row">
                                        <div class="col-12">
                                            <p class="card-text">
                                                ${article.body}
                                            </p>


                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class=" float-right">
                                        <#list article.tagList as artTag >
                                            <span class="badge badge-pill badge-info ml-1">${artTag.tag}</span>
                                        </#list>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <a class="card-link" id="like" value="${article.id}" style="<#assign aux = false><#if user??><#if article.getUserLike(user.username)??><#if article.getUserLike(user.username).liked == true>color: #0b51c5;<#assign aux = true><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if>"  ><i class="fa fa-thumbs-up"></i><span class="<#if aux == true>active</#if>"><#assign number = article.getNumbersOfLikes()><#if (number > 0)> ${number}</#if></span></a>
                                    <a class="card-link" id="dislike" value="${article.id}" style="<#assign aux = false><#if user??><#if article.getUserLike(user.username)??><#if article.getUserLike(user.username).liked == false>color: #0b51c5;<#assign aux = true><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if>"> <i class="fa fa-thumbs-down"></i><span class="<#if aux == true>active</#if>"><#assign number = article.getNumbersOfDislikes()><#if (number > 0)> ${number}</#if></span></a>

                                </div>
                            </div>


                    </#if>

                </div>
            </div>
            <!-- Post model /////-->
            <div class="row">
                <div class="col-sm-12">
                    <!--Section: Comments--><div class="modal fade" id="basicExampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

                    <section class="my-5">

                        <!-- Card header -->
                        <div class="card-header border-0 font-weight-bold"><#if article??>${article.getNumbersOfComments()}</#if> comments</div>

                        <#list article.commentList as comment>
                        <div class="media d-block d-md-flex mt-4">
                            <img class="card-img-64 d-flex mx-auto mb-3" src="https://mdbootstrap.com/img/Photos/Avatars/img (20).jpg"
                                 alt="Generic placeholder image">
                            <div class="media-body text-center text-md-left ml-md-3 ml-0">
                                <#if user??>
                                    <#if user.administrator || user.username == article.author.username || comment.author.username == user.username >
                                    <div class="float-right">
                                        <span class=""><a href="/deleteComment/${comment.id}"><i class="far fa-trash-alt"></i></a></span>
                                    </div>
                                </#if>
                                </#if>

                                <h5 class="font-weight-bold mt-0">
                                    <a href="">${comment.author.name}</a>
                                    <a href="" class="pull-right">
                                        <i class="fas fa-reply"></i>
                                    </a>
                                </h5>
                                <p>${comment.comment}</p>

                                <!-- Quick Reply -->
                                <div class="form-group mt-4">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <a class="card-link" id="commentLike" value="${comment.id}" style="<#assign aux = false><#if user??><#if comment.getUserLike(user.username)??><#if comment.getUserLike(user.username).liked == true>color: #0b51c5;<#assign aux = true><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if>"  ><i class="fa fa-thumbs-up"></i><span class="<#if aux == true>active</#if>"><#assign number = comment.getNumbersOfLikes()><#if (number > 0)> ${number}</#if></span></a>
                                            <a class="card-link" id="commentDislike" value="${comment.id}" style="<#assign aux = false><#if user??><#if comment.getUserLike(user.username)??><#if comment.getUserLike(user.username).liked == false>color: #0b51c5;<#assign aux = true><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if><#else>color: #90a4ae;</#if>"> <i class="fa fa-thumbs-down"></i><span class="<#if aux == true>active</#if>"><#assign number = comment.getNumbersOfDislikes()><#if (number > 0)> ${number}</#if></span></a>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </#list>
                        <div class="row">
                            <div class="col-sm-12">
                                <form action="/registerComment/${article.id}" method="post" id="comment-form">
                                    <label for="quickReplyFormComment">Your comment</label>
                                    <textarea class="form-control" id="quickReplyFormComment" name="quickReplyFormComment" rows="5" form="comment-form"></textarea>

                                    <div class="text-center my-4">
                                        <button class="btn btn-primary btn-sm" type="submit">Comentar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!--Pagination -->

                    </section>
                    <!--Section: Comments-->

                </div>
            </div>





            <!-- Post /////-->
            <!--- \\\\\\\Post model-->

            <!-- Post model /////-->



        </div>
        <div class="col-md-3 ">
            <div class=" position-fixed" id="right-column">


                <form action="/loadArticles/0" class="form-inline my-1" method="get">
                    <div class="md-form form-sm my-0 ml-auto">
                        <input class="form-control form-control-sm mr-sm-2 mb-0 ml-1" name="search" id="search" type="text" placeholder="Buscar Tag" aria-label="Buscar Tag" value="<#if filterTag??>${filterTag}</#if>">
                    </div>
                    <button class="btn btn-sm my-0 info-color " type="submit"><span style="color: white;"><i class="fas fa-search"></i></span> </button>
                </form>


            </div>

        </div>
    </div>
</div>



<!-- Modal: modalCart -->
<div class="modal fade" id="modalListUser" tabindex="1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                <table class="table table-hover" id="listUsers">
                    <tbody class="rows-in-table"></tbody>
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
                        <input type="text" id="inputMDEx" class="form-control input-full-name-modal-register-update" name="username" required>
                        <label for="inputMDEx" class="input-full-name-modal-register-update">Nombre de usuario</label>
                    </div>
                    <div class="md-form">
                        <input type="text" id="inputMDEx" class="form-control name-input" name="name" required>
                        <label for="inputMDEx" class="name-input">Nombre completo</label>
                    </div>
                    <div class="md-form">
                        <input type="password" id="inputMDEx" class="form-control password-input" name="password" required>
                        <label for="inputMDEx" class="password-input">Password</label>
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
</form><!-- Central Modal Small -->

<!--Modal confirm delete-->
<!--Modal: modalConfirmDelete-->
<div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm modal-notify modal-danger" role="document">
        <!--Content-->
        <div class="modal-content text-center">
            <!--Header-->
            <div class="modal-header d-flex justify-content-center">
                <p class="heading" id="confirmTitle">Eliminar usuario</p>
            </div>

            <!--Body-->
            <div class="modal-body">

                <i class="fas fa-times fa-4x animated rotateIn"></i>
                <div class="row">
                    <div class="col">
                        <span class="messageDelete"> ${article.title}</span>
                    </div>
                </div>


            </div>

            <!--Footer-->
            <div class="modal-footer flex-center">
                <a href="/deleteArticle/${article.id}" class="btn  btn-outline-danger" type="submit">Si!</a>
                <a type="button" class="btn  btn-danger waves-effect" data-dismiss="modal">Cancelar</a>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>


<div class="modal fade" id="updateArticle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">

    <!-- Change class .modal-sm to change the size of the modal -->
    <div class="modal-dialog modal-lg" role="document">


        <div class="modal-content">

            <div class="modal-body">
                <div class="card gedf-card">
                    <div class="card-header">
                       <div class="float-right">
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                               <span aria-hidden="true">&times;</span>
                           </button>

                       </div>
                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Editar Publicación</a>
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
                                                <input type="hidden" id="hidden">
                                                <input type="text" id="inputSMEx" class="pubTitle form-control form-control-sm name-article" name="name-article">
                                                <label for="inputSMEx" class="title-update">Título</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="md-form form-sm">
                                                <input type="text" id="inputSMEx" class="pubPublication form-control form-control-sm tags-input">
                                                <label for="inputSMEx">Etiquetas</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-2">
                                            Tags:

                                        </div>
                                        <div class="col-10">
                                            <div class="tags">


                                            </div>

                                        </div>
                                    </div>

                                    <textarea class="form-control exampleFormControlTextarea5 textArticle" id="exampleFormControlTextarea5" rows="3" name="textArticle"></textarea>
                                </div>
                                <div class="btn-group">
                                    <a class="btn btn-primary postArticle-update" id="postArticle-update" type="submit">Publicar</a>
                                </div>
                                <!--Form for article to submit-->
                            </div>
                        </div>

                    </div>
                    <!--Input field for post-->
                </div>
            </div>

        </div>
        <div class="btn-toolbar justify-content-between float-right">


        </div>
    </div>
</div>
<!-- Central Modal Small -->
<!-- Start your project here-->
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="/js/mdb.min.js"></script>
<script type="text/javascript" src="/js/javaScript.js"></script>
<script type="text/javascript" src="/js/listUsers.js"></script>
</body>
</html>
