<%
    String error = (String)session.getAttribute("error");
    String url = (String)session.getAttribute("url");

    if(error==null||url==null){
        response.sendRedirect("index");
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .main {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: rgba(127, 255, 212, 0.5);
            flex-direction: column;
            padding: 50px 0;
        }

        button {
            background-color: aqua;
            border: 0;
            padding: 10px 20px;
            color: white;
            border-radius: 8px;
            font-weight: 550;
            font-size: 15px;
        }

        span {
            margin-bottom: 20px;
        }

        .add-card-box {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border: 2px dashed gray;
            background-color: white;
            padding: 20px 30px;
            border-radius: 8px;
        }
    </style>
</head>

<body>
    <div class="main">
        <div class="add-card-box">
            <span><%=error%></span>
            <a href="<%=url%>">
                <button>Go Back</button>
            </a>
        </div>
    </div>
</body>

</html>

<%
    session.removeAttribute("error");
    session.removeAttribute("url");
%>