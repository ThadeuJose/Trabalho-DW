<%-- 
    Document   : index
    Created on : Nov 21, 2016, 8:33:23 PM
    Author     : PauloRoberto

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title> AA Airlines</title>
    </head>
    <body>
        <%             
            if (request.getAttribute("logou") == null){
                request.setAttribute("logou", false); 
            }if(request.getAttribute("logou").equals(false)){
                %> <a href="login.html">Login</a><%
            }
        %>
        <a href="">Check In</a>
        <form>
            <div>
                Origem: <input type="text" id="De" name="De"/>
                Data de ida: <input type="text" id="DataDe" name="DataDe"/>
            </div> 
            <div>
                Destino: <input type="text" id="Para" name="Para"/>
                Data de volta: <input type="text" id="DataPara" name="DataPara"/>
            </div>
            Adulto: <input type="text" id="Username" name="Username"/>
            CrianÃ§a: <input type="text" id="Username" name="Username"/>
            BebÃª: <input type="text" id="Username" name="Username"/><br>
            <button>Pesquisar</button><button>Reset</button>
        </form>
    </body>
</html>
