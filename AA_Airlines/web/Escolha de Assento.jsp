<%-- 
    Document   : Escolha de Assento
    Created on : 26/11/2016, 18:37:36
    Author     : Thadeu Jose
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escolha de Assentos</title>
    </head>
    <body>
        <form id="EscAssento" >
            Escolha de assentos: <br>
            <%= request.getAttribute("tipo")%><br>
            Nome: <input type="text" id="nome" name="nome"/><br>
            Sobrenome: <input type="text" id="sobrenome" name="sobrenome"/><br>
            Sexo: <input type="text" id="sexo" name="sexo"/><br>
            CPF:<input type="text" id="cpf" name="cpf"/><br>
            Passaporte:<input type="text" id="passaporte" name="passaporte"/><br>
            Assento:
            <select id="assento" name="assento">
                <%
                    ArrayList<Integer> listAss=(ArrayList<Integer>) request.getAttribute("listAss"); 
                    for (Integer ass: listAss) {   
                %>
                <option value="<%=ass%>"><%=ass%></option>
                <%}%>                
            </select> 
            <br>
            <button formaction="EscAssentoServlet">Confirmar</button><button>Reset</button>
        </form>
    </body>
</html>
