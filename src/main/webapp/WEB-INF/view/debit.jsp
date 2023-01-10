<%@page
import="com.ud.debitwallet.model.User"
%>

<%
    User user = (User)session.getAttribute("user");
    if(user==null){
        response.sendRedirect("index");
    }else if(user.getDebitCard()!=null){
        response.sendRedirect("dashboard");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate Debit Card - Debit Wallet</title>
    <style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .main{
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: rgba(127, 255, 212, 0.5);
        }
        form{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: white;
            height: 450px;
            padding: 0 20px;
            border-radius: 20px;
            box-shadow:2px 2px 2px 2px gray;
        }
        form input{
            border: 0;
            background-color: rgba(128, 128, 128, 0.2);
            padding: 15px;
            width: 250px;
            border-radius: 8px;
            margin: 20px 0;
        }
        button{
            background-color: aqua;
            border: 0;
            padding: 10px 20px;
            color: white;
            border-radius: 8px;
            font-weight: 550;
            font-size: 15px;
        }
    </style>
</head>
<body>
    <div class="main">
        <form action="generate_debit_card" method="post">
            <label for="pin">Pin</label>
            <input type="number" name="pin" id="pin" placeholder="Enter 4 digit pin" maxlength="4">

            <button type="submit">Generate</button>
        </form>
    </div>
</body>
</html>