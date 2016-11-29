<%-- 
    Document   : Escolha de Assento
    Created on : 26/11/2016, 18:37:36
    Author     : Thadeu Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escolha de assentos</title>
    </head>
    <body>
        <form id="EscAss" action="EscAssentoServlet">
            Escolha de assentos <br>
            <%= request.getAttribute("tipo")%><br>
            Nome: <input type="text" id="nome" name="nome"/><br>
            Sobrenome: <input type="text" id="sobrenome" name="sobrenome"/><br>
            Sexo: <input type="text" id="sexo" name="sexo"/><br>
            Assento:
            <select id="assento" name="assento">
                
                
            </select> 
            <br>
            <button>Confirmar</button><button>Reset</button>
        </form>
    </body>
</html>
