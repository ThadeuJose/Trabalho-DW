<%-- 
    Document   : CartaoEmbarque
    Created on : Dec 12, 2016, 10:20:07 PM
    Author     : PauloRoberto
--%>

<%@page import="ClassesHib.Passageiros"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        Cartao de Embarque: Imprima esta pagina para embarcar.<br>    
        <br> 
        Voo de Ida:<br> 
        Numero do Voo: <%= request.getSession().getAttribute("vooIdaCheckin") %><br> 
        <%= request.getSession().getAttribute("aerosIdaCheck") %><br> 
        <%= request.getSession().getAttribute("dataIdaCheck") %><br> 
        <%= request.getSession().getAttribute("horaIdaCheck") %><br> 
        <br> 
        Voo de Volta:<br>
        Numero do Voo: <%= request.getSession().getAttribute("vooVoltaCheckin") %><br> 
        <%= request.getSession().getAttribute("aerosVoltaCheck") %><br> 
        <%= request.getSession().getAttribute("dataVoltaCheck") %><br> 
        <%= request.getSession().getAttribute("horaVoltaCheck") %><br>
        <br> 
        Passageiros:<br><br>
        <%
            ArrayList<Passageiros> listaPas = (ArrayList<Passageiros>) request.getSession().getAttribute("listaPassageiros");
            ArrayList<String> assentosIda = (ArrayList<String>) request.getSession().getAttribute("assentosIda");
            ArrayList<String> assentosVolta = (ArrayList<String>) request.getSession().getAttribute("assentosVolta");
            
            for (int i = 0; i < listaPas.size(); i++) {
                    %> Passageiro:          <%=listaPas.get(i).getNmpas()%> <%=listaPas.get(i).getSnpas()%><br><%
                    %> Assento na Ida:      <%=assentosIda.get(i)  %><br><%
                    %> Assento na Volta:    <%=assentosVolta.get(i)  %><br><%
                    %> Endereco:    <%=listaPas.get(i).getEndpas()  %><br><%
                    %> Telefone:    <%=listaPas.get(i).getTelpas()  %><br><br><br><%
            }

                    %>Preco Ida: <%=request.getSession().getAttribute("precoIda") %><br><%
                    %>Preco Volta: <%=request.getSession().getAttribute("precoVolta") %><br><%
                    %>Total: <%= (Double)request.getSession().getAttribute("precoIda") + (Double) request.getSession().getAttribute("precoVolta") %><br><%
                    %>Taxa Embarque Ida(Ja incluida no preco): <%=request.getSession().getAttribute("taxaIda") %><br><%
                    %>Taxa Embarque Volta(Ja incluida no preco): <%=request.getSession().getAttribute("taxaVolta") %><br><%
        
        %>
        <br> 
        <input type="submit" value="Voltar" formaction="index.jsp"/>
    </body>
</html>
