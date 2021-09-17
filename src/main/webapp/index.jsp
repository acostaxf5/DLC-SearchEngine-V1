<%@ page import="Gestores.GestorVocabulario" %>

<%
    if (GestorVocabulario.vocabulario == null)
    {
        GestorVocabulario.cargarVocabulario();
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>DLC - MOTOR DE BÚSQUEDA</title>
        <meta charset="UTF-8"/>
        <link rel="icon" href="recursos/imagenes/favicon.png"/>
        <link rel="stylesheet" href="recursos/css/bootstrap.css"/>
        <link rel="stylesheet" href="recursos/css/estilos.css"/>
    </head>
    <body>
        <!-- JUMBOTRON - HEADER -->
        <div id="jumbotron" class="jumbotron jumbotron-fluid">
            <div id="logo" class="container flexContainer">
                <img id="icono" src="recursos/imagenes/motor.png" class="img-fluid" alt="WEB-SEARCH"/>
                <b>DLC - MOTOR DE BÚSQUEDA</b>
            </div>
            <div id="boton">
                <form method="POST" action="/DLC-SearchEngine/API/IndexadorWeb/cargarDocumentos" enctype="multipart/form-data">
                    <label for="cargador">¿DESEA CARGAR ARCHIVOS AL MOTOR?</label>
                    <input name="file" id="cargador" type="file" class="btn btn-light font-weight-bold" accept=".txt" multiple>
                    <div>
                        <button class="btn btn-light font-weight-bold">CARGAR ARCHIVOS</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- PRINCIPAL -->
        <form method="post" action="API/Buscador/obtenerBusqueda">
            <div id="cuerpoBuscador" class="container cuerpoBuscador">
                <div class="input-group mb-3 contenedorBuscador">
                    <input name="consulta" type="text" class="form-control inputBusqueda" placeholder="¿QUÉ DESEA BUSCAR?" required>
                    <div class="input-group-append">
                        <input class="btn btnBuscar" type="submit" value="BUSCAR"/>
                    </div>
                </div>
                <div class="cantidadResultados">
                    <h4><b>¿CUÁNTOS RESULTADOS DESEA VISUALIZAR?</b></h4>
                    <div>
                        <input name="cantidad" type="number" value="20" required/>
                    </div>
                </div>
            </div>
        </form>

        <!-- FOOTER -->
        <footer class="footer">
            <div class="container seccionFooter">
                <div class="imagenes">
                    <img id="java" class="img-fluid tecnologia" src="recursos/imagenes/java.png" alt="JAVA"/>
                    <img id="html" class="img-fluid tecnologia" src="recursos/imagenes/html.png" alt="HTML"/>
                    <img id="css" class="img-fluid tecnologia" src="recursos/imagenes/css.png" alt="CSS"/>
                </div>
                <div class="imagenes">
                    <img id="bootstrap" class="img-fluid tecnologia" src="recursos/imagenes/bootstrap.png" alt="BOOTSTRAP"/>
                    <img id="postgresql" class="img-fluid tecnologia" src="recursos/imagenes/postgresql.png" alt="POSTGRESQL"/>
                    <img id="payara" class="img-fluid tecnologia" src="recursos/imagenes/payara.png" alt="PAYARA"/>
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
            document.getElementById("cuerpoBuscador").style.height = (height - 391) + "px";
        </script>
    </body>
</html>
