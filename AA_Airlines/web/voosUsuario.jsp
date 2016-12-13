<%-- 
    Document   : voosUsuario
    Created on : Dec 13, 2016, 7:24:08 PM
    Author     : PauloRoberto
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            ArrayList<String> voos = (ArrayList<String>) request.getSession().getAttribute("listaVoosUsuario");
            %> Seus voos sao esses:<br><br>  <%
            for (int i = 0; i < voos.size(); i++) {
                %>                
                  <%=voos.get(i)%><br>
                <%
                if(i%5 == 4){
                    %><br><br><%
                }
            }          
        %>
        <form><input type="submit" value="voltar" formaction="index.jsp"/></form>
    </body>
</html>
