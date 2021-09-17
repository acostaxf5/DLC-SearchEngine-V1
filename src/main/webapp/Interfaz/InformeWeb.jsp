<%@page import="Servicios.IndexadorWeb"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String mensajeOK = IndexadorWeb.mensajeOK;
    String tiempoInsumido = IndexadorWeb.tiempoInsumido;
    String raiz = IndexadorWeb.raiz;
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>DLC - MOTOR DE BÚSQUEDA</title>
        <meta charset="UTF-8"/>
        <link rel="icon" href="../recursos/imagenes/favicon.png"/>
        <link rel="stylesheet" href="../recursos/css/bootstrap.css"/>
        <link rel="stylesheet" href="../recursos/css/estilos.css"/>
    </head>
    <body>
        <!-- JUMBOTRON - HEADER -->
        <div id="jumbotron" class="jumbotron jumbotron-fluid">
            <div id="logo" class="container flexContainer">
                <img id="icono" src="../recursos/imagenes/motor.png" class="img-fluid" alt="WEB-SEARCH"/>
                <b>DLC - MOTOR DE BÚSQUEDA</b>
            </div>
        </div>

        <!-- PRINCIPAL -->
        <div id="contenedor" class="container">
            <div id="progreso" class="container">
                <h4 id="h41" class="text-center font-weight-bold"> <%= mensajeOK %> </h4>
                <h4 id="h41" class="text-center font-weight-bold"> <%= tiempoInsumido %> </h4>
                <div class="text-center mt-4">
                    <form method="post" action=" <%= raiz %> ">
                        <input type="submit" value="VOLVER AL BUSCADOR" class="btn btn-success font-weight-bold"/>
                    </form>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <footer class="footer">
            <div class="container seccionFooter">
                <div class="imagenes">
                    <img id="java" class="img-fluid tecnologia" src="../recursos/imagenes/java.png" alt="JAVA"/>
                    <img id="html" class="img-fluid tecnologia" src="../recursos/imagenes/html.png" alt="HTML"/>
                    <img id="css" class="img-fluid tecnologia" src="../recursos/imagenes/css.png" alt="CSS"/>
                </div>
                <div class="imagenes">
                    <img id="bootstrap" class="img-fluid tecnologia" src="../recursos/imagenes/bootstrap.png" alt="BOOTSTRAP"/>
                    <img id="postgresql" class="img-fluid tecnologia" src="../recursos/imagenes/postgresql.png" alt="POSTGRESQL"/>
                    <img id="payara" class="img-fluid tecnologia" src="../recursos/imagenes/payara.png" alt="PAYARA"/>
                </div>
            </div>
            <div class="container seccionFooter">
                <b>
                    UTN - FRC - DLC <br/>
                    4K06 <br/>
                    TPU-G004
                </b>
            </div>
            <div class="container seccionFooter">
                <b>INTEGRANTES</b>
                <p>
                    <b>
                        ACOSTA, FACUNDO LEONEL <br/>
                        COCUZZA, CAMILA <br/>
                        FERNANDEZ, DAMIAN ARNALDO <br/>
                        PAILLET, LUCAS MARIO
                    </b>
                </p>
            </div>
        </footer>

        <!-- ZONA DE SCRIPTS -->
        <script src="recursos/js/jquery-3.5.1.slim.min.js"></script>
        <script src="recursos/js/bootstrap.js"></script>
        <script>
            const height = window.innerHeight;
            document.getElementById("progreso").style.height = (height - 380) + "px";
        </script>
    </body>
</html>
