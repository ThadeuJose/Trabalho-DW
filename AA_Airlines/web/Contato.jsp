<%-- 
    Document   : Contato
    Created on : Dec 12, 2016, 6:49:29 PM
    Author     : PauloRoberto
--%>

<%@page import="ClassesHib.Passageiros"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            ArrayList<Passageiros> listaPas = (ArrayList<Passageiros>) request.getSession().getAttribute("listaPassageiros");
        %><form method="get" id="contato" action="Contato"><%
            for (int i = 0; i < listaPas.size(); i++) {
                    %> Passageiro: <%=listaPas.get(i).getNmpas()%> <%=listaPas.get(i).getSnpas()%><br><%
                    %> Endereco: <input type="text" id="Endereco<%=i+1%>" name="Endereco<%=i+1%>"/><br> <%
                    %> Telefone: <input type="text" id="Telefone<%=i+1%>" name="Telefone<%=i+1%>"/><br> <%
                    %><br><%
                }
            %>
                <input type="submit" value="Entrar" /> <br>
                <input type="submit" value="Cancelar" formaction="index.jsp"/>
            <%
            %></form><%
        %>
    </body>
</html>
