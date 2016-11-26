<%-- 
    Document   : Escolha de Voo
    Created on : 26/11/2016, 09:52:42
    Author     : Thadeu Jose
--%>

<%@page import="ClassesHib.Usuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.EscolhaVoo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escolha de Voo</title>
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
        <form>
            Ida<br>
            Data: <%= request.getAttribute("DataIda")%><br>
            Horario|Preços<br>
            <select id="VooIda" name="VooIda">
                <%
                    ArrayList<EscolhaVoo> listIda=(ArrayList<EscolhaVoo>) request.getAttribute("listIda"); 
                    for (EscolhaVoo ev: listIda) {   
                %>
                <option value="<%=ev.toString()%>"><%=ev.toString()%></option>
                <%}%>
            </select> 
            <br>    
            Volta<br>
            Data: <%= request.getAttribute("DataVolta")%><br>
            Horario|Preços<br>
            <select id="VooIda" name="VooIda">
                <%
                    ArrayList<EscolhaVoo> listVolta=(ArrayList<EscolhaVoo>) request.getAttribute("listVolta"); 
                    for (EscolhaVoo ev: listVolta) {   
                %>
                <option value="<%=ev.toString()%>"><%=ev.toString()%></option>
                <%}%>
            </select> 
            <br>    
            <button>Proseguir</button>            
    </body>
</html>
