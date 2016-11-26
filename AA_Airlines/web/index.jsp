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
            if (request.getAttribute("logou") == null){
                request.setAttribute("logou", false); 
            }if(request.getAttribute("logou").equals(false)){
                %> <a href="login.html">Login</a><%
            }
            if (request.getAttribute("usuarioLogado") != null){
                usuarioLogado = (Usuarios) request.getAttribute("usuarioLogado");
                %> Bem vindo(a), <%= usuarioLogado.getNmusu() %> <% 
            }
        %>
        <a href="">Check In</a>
        <form action="BuscaServlet" method="post">
            <div>
                Origem: <input type="text" id="De" name="De"/>
                Data de ida: <input type="text" id="DataDe" name="DataDe"/>
            </div> 
            <div>
                Destino: <input type="text" id="Para" name="Para"/>
                Data de volta: <input type="text" id="DataPara" name="DataPara"/>
            </div>
            Adulto: <input type="text" id="numAdultos" name="numAdultos"/>
            Crianca: <input type="text" id="numCriancas" name="numCriancas"/>
            Bebe: <input type="text" id="numBebes" name="numBebes"/><br>
            <select id="Categoria" name="Categoria" >
            <option value="1">Promoção</option>
            <option value="2">Economica</option>
            <option value="3">Primeira Classe</option>  
            </select> 
            <br>
            <button>Pesquisar</button><button>Limpar</button>
        </form>
    </body>
</html>
