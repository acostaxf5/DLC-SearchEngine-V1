<%@page import="java.util.List"%>
<%@page import="Entidades.Documento"%>
<%@page import="Servicios.Buscador"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% 
    String contextPath = Buscador.contextPath; 
    String consulta = Buscador.consulta;
    int cantidad = Buscador.cantidad;
    List<Documento> respuestaDocumentos = Buscador.respuestaDocumentos;
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
        <%
            if (respuestaDocumentos.size() == 0)
            {
        %>
                <div id="contenedor" class="container">
                    <div id="progreso" class="container">
                        <h3 class="text-center mb-2"> <b>RESULTADOS DE LA BÚSQUEDA</b> </h3>
                        <h5 class="text-center text-dark text-uppercase"> <b> <%= consulta %> </b> </h5>
                        <h5 class="text-center text-dark text-uppercase"> <b> SIN RESULTADOS </b> </h5>
                        <div class="text-center mt-4">
                            <form method="post" action=" <%= contextPath %> ">
                                <input type="submit" value="VOLVER AL BUSCADOR" class="btn btn-success font-weight-bold"/>
                            </form>
                        </div>
                    </div>
                </div>
        <%
            }
            else
            {
        %>
                <div class="list-group container">
                    <form method="post" action=" <%= contextPath %> "\>
                        <input class="btn btn-success btnBuscar font-weight-bold" type="submit" value="VOLVER AL BUSCADOR"/>
                    </form>
                    <h3 class="text-center mb-2"> <b>RESULTADOS DE LA BÚSQUEDA</b> </h3>
                    <h5 class="text-center text-dark text-uppercase"> <b> <%= consulta %> </b> </h5>
                    <%
                        if (cantidad <= respuestaDocumentos.size())
                        {
                    %>
                            <h5 class="text-center mb-4 text-success"> <b> MOSTRANDO <%= cantidad %> RESULTADOS </b> </h5>
                    <%
                        }
                        else
                        {
                    %>
                            <h5 class="text-center mb-4 text-success"> <b> MOSTRANDO <%= respuestaDocumentos.size() %> RESULTADOS </b> </h5>
                    <%
                        }

                        int contadorDocumentos = 0;
                        for (Documento documento : respuestaDocumentos)
                        {
                            contadorDocumentos++;

                            double indiceRelevancia = documento.getIndiceRelevancia();
                            String nombreDocumento = documento.getNombreDocumento();
                    %>
                            <div class="list-group-item list-group-item-action">
                                <p style="color: red; margin-bottom: 0px;">
                                    <b>IR:</b> <%= indiceRelevancia %>
                                </p>
                                <p style="margin-bottom: 0px">
                                    <b>ARCHIVO:</b> <%= nombreDocumento %>
                                </p>
                                <p style="margin-bottom: 0px">
                                    <b>LINK: </b>
                                    <a target="_blank" href="../BDD/<%= nombreDocumento %>"> <%= nombreDocumento %> <a/>
                                </p>
                            </div>
                    <%
                            if (contadorDocumentos == cantidad)
                            {
                                break;
                            }

                        }
                    %>
                </div>
        <%
            }
        %>

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
            document.getElementById("progreso").style.height = (height - 381) + "px";
        </script>
    </body>
</html>
