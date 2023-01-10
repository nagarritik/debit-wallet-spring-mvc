<%
    if(session.getAttribute("user")==null){
        response.sendRedirect("login");
    }else{
        response.sendRedirect("dashboard");
    }
%>