<%-- 
    Document   : MudaAssento
    Created on : 13/12/2016, 10:58:05
    Author     : Antonio
--%>
<%@page import="ClassesHib.Passageiros"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mudança de Assentos</title>
    </head>
    <body>
        <h0>Mudança de Assentos</h0><br>
        <%
            ArrayList<Passageiros> listaPas = (ArrayList<Passageiros>) request.getSession().getAttribute("listaPassageiros");
            ArrayList<String> assentosIda = (ArrayList<String>) request.getSession().getAttribute("assentosIda");
            ArrayList<String> assentosVolta = (ArrayList<String>) request.getSession().getAttribute("assentosVolta");
            ArrayList<Integer> listAss = (ArrayList<Integer>) request.getSession().getAttribute("listAss");
        %>
        <form method="get" id="modAssento" action="MudaAssento">
            Voo de Ida:<br> 
            Numero do Voo: <%= request.getSession().getAttribute("vooIdaCheckin") %><br> 
            <%= request.getSession().getAttribute("aerosIdaCheck") %><br> 
            <%= request.getSession().getAttribute("dataIdaCheck") %><br> 
            <%= request.getSession().getAttribute("horaIdaCheck") %><br>
            <br> 
                <%for (int i = 0; i < listaPas.size(); i++) {%> 
                    Passageiro: <%=listaPas.get(i).getNmpas()%> <%=listaPas.get(i).getSnpas()%><br>
                    Assento na Ida:<%=assentosIda.get(i)%><br>
                    <select id = "modAssIda" name ="modAssIda"> 
                    <%for (int idx = 0; idx < listAss.size(); idx++){%>
                    <option value="<%= listAss.get(idx) %>"><%= listAss.get(idx) %> </option>
                    <%}%>
                    </select> 
                <%}%>
                
                
                <br>
                Voo de Volta:<br>
                Numero do Voo: <%= request.getSession().getAttribute("vooVoltaCheckin") %><br> 
                <%= request.getSession().getAttribute("aerosVoltaCheck") %><br> 
                <%= request.getSession().getAttribute("dataVoltaCheck") %><br> 
                <%= request.getSession().getAttribute("horaVoltaCheck") %><br>
                    <br> 
                <%
                for (int i = 0; i < listaPas.size(); i++) {
                    %> Passageiro: <%=listaPas.get(i).getNmpas()%> <%=listaPas.get(i).getSnpas()%><br><%
                    %> Assento na Volta:      <%=assentosVolta.get(i)  %><br><%                    
                }
            %>
                <input type="submit" value="Continuar" />
                <input type="submit" value="Cancelar" formaction="index.jsp"/>
            <%
            %></form><%
        %>
    </body>
</html>
