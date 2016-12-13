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
            if (request.getSession().getAttribute("logou") == null){
                request.getSession().setAttribute("logou", false); 
            }if(request.getSession().getAttribute("logou").equals(false)){
                %> <a href="login.html">Login</a><%
            }
            if (request.getSession().getAttribute("usuarioLogado") != null){
                usuarioLogado = (Usuarios) request.getSession().getAttribute("usuarioLogado");
                %> Bem vindo(a), <%= usuarioLogado.getNmusu() %> <% 
            }
        %><br><br>
        <form method="post">
            Ida<br>
            Data: <%= request.getAttribute("DataIda")%><br>
            Horario|Preços<br>
            <select id="vooIda" name="vooIda">
                <%
                    ArrayList<EscolhaVoo> listIda=(ArrayList<EscolhaVoo>) request.getAttribute("listIda"); 
                    for (EscolhaVoo ev: listIda) {   
                %>
                <option value="<%=ev.info()%>"><%=ev.toString()%></option>
                <%}%>
            </select> 
            <br>    
            Volta<br>
            Data: <%= request.getAttribute("DataVolta")%><br>
            Horario|Preços<br>
            <select id="vooVolta" name="vooVolta">
                <%
                    ArrayList<EscolhaVoo> listVolta=(ArrayList<EscolhaVoo>) request.getAttribute("listVolta"); 
                    for (EscolhaVoo ev: listVolta) {   
                %>
                <option value="<%=ev.info()%>"><%=ev.toString()%></option>
                <%}%>
            </select> 
            <br>    
            <input type="submit" name="Prosseguir" value="Prosseguir" formaction="PrecoTotal" >            
    </body>
</html>
