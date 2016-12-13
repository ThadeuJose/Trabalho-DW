<%-- 
    Document   : PrecoTotal
    Created on : Nov 26, 2016, 3:41:03 PM
    Author     : PauloRoberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Preco Total</title>
    </head>
    <body>
        <form id="PrecoTotal" action="PrecoTotalServlet">
            Ida:<br>
            De: <%= request.getAttribute("cidadeIdaOri")%><br>
            Para: <%= request.getAttribute("cidadeIdaDest")%><br>
            <%= request.getAttribute("horaIda")%><br>
            R$<%= request.getAttribute("precoIda")%><br>        
            <br>
            <br>
            Volta:<br>
            De: <%= request.getAttribute("cidadeVoltaOri")%><br> 
            Para: <%= request.getAttribute("cidadeVoltaDest")%><br>
            <%= request.getAttribute("horaVolta")%><br>
            R$<%= request.getAttribute("precoVolta")%><br>
            <br>
            <br>
            Total:  R$<%= request.getAttribute("precoTotal")%>
        </form>
    </body>
</html>
