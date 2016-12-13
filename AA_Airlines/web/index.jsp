<%-- 
    Document   : index
    Created on : Nov 21, 2016, 8:33:23 PM
    Author     : PauloRoberto

--%>

<%@page import="ClassesHib.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title> AA Airlines</title>
    </head>
    <body>
        <%
            Usuarios usuarioLogado;
            if (request.getSession().getAttribute("logou") == null){
                request.getSession().setAttribute("logou", false); 
            }if(request.getSession().getAttribute("logou").equals(false)){
                %> <a href="login.html">Login</a><%
            }
            if (request.getSession().getAttribute("usuarioLogado") != null){
                usuarioLogado = (Usuarios) request.getSession().getAttribute("usuarioLogado");
                %> Bem vindo(a), <%= usuarioLogado.getNmusu() %> <% 
            }
        %>
        <a href="checkin.html">Check In</a><br>
        <form>
            <div>
                Origem: <input type="text" id="De" name="De"  value="Rio de Janeiro"/>
                Data de ida: <input type="text" id="DataDe" name="DataDe" value="2016-12-12"/>
            </div> 
            <div>
                Destino: <input type="text" id="Para" name="Para" value="Sao Paulo"/>
                Data de volta: <input type="text" id="DataPara" name="DataPara" value="2016-12-12"/>
            </div>
            Adulto: <input type="text" id="numAdultos" name="numAdultos" value="1"/>
            Crianca: <input type="text" id="numCriancas" name="numCriancas" value="1"/>
            Bebe: <input type="text" id="numBebes" name="numBebes" value="1"/><br>
            <select id="classe" name="classe">
                <option value="1">Promoção</option>
                <option value="2">Economica</option>
                <option value="3">Primeira Classe</option>  
            </select>
            <button formaction="BuscaServlet">Pesquisar</button><button>Reset</button>
        
    </body>
</html>
