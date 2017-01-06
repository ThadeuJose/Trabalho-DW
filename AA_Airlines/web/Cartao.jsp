<%-- 
    Document   : Pagamento
    Created on : 13/12/2016, 09:55:29
    Author     : Thadeu Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cartão de Embarque</title>
    </head>
    <body>        
        <form>
            Seu codigo de embarque é: <%= request.getAttribute("codRes")%>
            <input type="submit" value="Voltar" formaction="index.jsp"/>
        </form>
    </body>
</html>