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
            ArrayList<String> naoOcupadosIda = (ArrayList<String>) request.getSession().getAttribute("naoOcupadosIda");
            ArrayList<String> naoOcupadosVolta = (ArrayList<String>) request.getSession().getAttribute("naoOcupadosVolta");
        %>
        <form method="get" id="modAssento" action="MudaAssentoServlet">
            Voo de Ida:<br> 
            Numero do Voo: <%= request.getSession().getAttribute("vooIdaCheckin") %><br> 
            <%= request.getSession().getAttribute("aerosIdaCheck") %><br> 
            <%= request.getSession().getAttribute("dataIdaCheck") %><br> 
            <%= request.getSession().getAttribute("horaIdaCheck") %><br>
            <br> 
                <%for (int i = 0; i < listaPas.size(); i++) {%> 
                    Passageiro: <%=listaPas.get(i).getNmpas()%> <%=listaPas.get(i).getSnpas()%><br>
                    Assento na Ida:<%=assentosIda.get(i)%><br><br>
                    Se deseja mudar de assento, escolha um na lista abaixo:<br>
                    <select id = "modAssIda<%= i%>" name ="modAssIda<%= i%>">
                    <option value="NAOMUDOU" >Selecione</option>
                    <%for (int idx = 0; idx < naoOcupadosIda.size(); idx++){%>
                    <option value="<%= naoOcupadosIda.get(idx) %>"><%= naoOcupadosIda.get(idx) %> </option>
                    <%}%>
                    </select> <br><br>    
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
                    %> Assento na Volta:      <%=assentosVolta.get(i)  %><br><br><%
                    %>Se deseja mudar de assento, escolha um na lista abaixo:<br>
                    <select id = "modAssVolta<%= i%>" name ="modAssVolta<%= i%>">
                    <option value="NAOMUDOU" >Selecione</option>
                    <%for (int idx = 0; idx < naoOcupadosVolta.size(); idx++){%>
                    <option value="<%= naoOcupadosVolta.get(idx) %>"><%= naoOcupadosVolta.get(idx) %> </option>
                    <%}%>
                    </select> <br><br><br>                                                        <%
                        
                }
            %>
                <input type="submit" value="Continuar" />
                <input type="submit" value="Cancelar" formaction="index.jsp"/>
            <%
            %></form><%
        %>
    </body>
</html>
