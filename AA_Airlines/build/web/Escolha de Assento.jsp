<%-- 
    Document   : Escolha de Assento
    Created on : 26/11/2016, 18:37:36
    Author     : Thadeu Jose
--%>

<%@page import="ClassesHib.Assentos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escolha de Assentos</title>
    </head>
    <body>
        <form id="EscAssento" method="post">
            Escolha de assentos: <br>
            <%for (int i = 0; i < (Integer)request.getAttribute("totalAss"); i++) {%>            
            Nome: <input type="text" id="nome<%=i%>" name="nome<%=i%>"/><br>
            Sobrenome: <input type="text" id="sobrenome<%=i%>" name="sobrenome<%=i%>"/><br>
            Telefone: <input type="text" id="telefone<%=i%>" name="telefone<%=i%>"/><br>
            Endereço: <input type="text" id="endereco<%=i%>" name="endereco<%=i%>"/><br>
            Identidade: <input type="text" id="identidade<%=i%>" name="identidade<%=i%>"/><br>
            CPF:<input type="text" id="cpf<%=i%>" name="cpf<%=i%>"/><br>
            Passaporte:<input type="text" id="passaporte<%=i%>" name="passaporte<%=i%>"/><br>
            Assento:
            <select id="assento<%=i%>" name="assento<%=i%>">
                <%
                    ArrayList<Assentos> listAss=(ArrayList<Assentos>) request.getAttribute("listAss"); 
                    for (Assentos ass: listAss) {   
                %>
                <option value="<%=ass.getIdass()%>"><%=ass.getNmass()%></option>
                <%}%>                
            </select> 
            <br><br>
            <%}%>            
            <hr>
            Pagamento<br>
            Cartão de Crédito<br> 
            Numero:<input type="text" id="numero" name="numero"/><br>
            Codigo de segurança:<input type="text" id="codseg" name="codseg"/><br>
            Data de Validade: <input type="text" id="dataval" name="dataval"/><br>
            <button formaction="Pagamento">Confirmar</button><button>Reset</button>
        </form>
    </body>
</html>
