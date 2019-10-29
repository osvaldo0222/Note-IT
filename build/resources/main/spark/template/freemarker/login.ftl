<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design Bootstrap</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.8.10/css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>

<body >
<style>
    #form-styles {
        background-color: white;
        padding: 15px;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translateX(-50%) translateY(-50%);
        -webkit-transform: translate(-50%,-50%);
        transform: translate(-50%,-50%);
    }
    body{
        background-image: url("https://webgradients.com/public/webgradients_png/019%20Malibu%20Beach.png");
    }
</style>

<!-- Start your project here-->
<div class="img">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="form-styles">
            <!-- Default form login -->
            <form class="text-center border border-light p-5"  action="/authenticate" method="post">

                <p class="h4 mb-4">Practica #3: BLOG</p>

                <!-- Email -->
                <input type="text" placeholder="Nombre" name="username" id="usuario" required class="form-control mb-4" value="<#if username??>${username}</#if>">

                <!-- Password -->
                <input type="password" placeholder="Contraseña" name="password" id="password" required class="form-control mb-4" value="<#if password??>${password}</#if>">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="defaultLoginFormRemember" id="defaultLoginFormRemember">
                    <label class="custom-control-label" for="defaultLoginFormRemember">Remember me</label>
                </div>
                <!-- Sign in button -->
                <button class="btn btn-info btn-block my-4" type="submit">Entrar</button>

                <#if incorrectPassword??>
                    <div class="alert alert-danger" role="alert">
                        ${incorrectPassword}
                    </div>
                </#if>

                <#if userNotFound??>
                    <div class="alert alert-danger" role="alert">
                        ${userNotFound}
                    </div>
                </#if>


            </form>
            <!-- Default form login -->

        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<!-- Start your project here-->

<!-- SCRIPTS -->
<!-- JQuery -->

</body>

</html>