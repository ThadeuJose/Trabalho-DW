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
        <title>Pagamento</title>
    </head>
    <body>        
        <form>
            Cartão de Crédito<br> 
            Numero:<input type="text" id="numero" name="numero"/><br>
            Codigo de segurança:<input type="text" id="codseg" name="codseg"/><br>
            Data de Validade: <input type="text" id="dataval" name="dataval"/><br>
            <button formaction="Pagamento" >Comprar</button>
        </form>
    </body>
</html>