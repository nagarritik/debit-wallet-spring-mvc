<%@page
import="com.ud.debitwallet.model.User"
import="com.ud.debitwallet.model.Transaction"
import="java.util.List"
%>

<%
    User user = (User)session.getAttribute("user");
    if(user==null){
        response.sendRedirect("index");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=user.getName()%> - Debit Wallet</title>
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
            flex-direction: column;
            padding: 50px 0;
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
        span{
            margin-bottom: 20px;
        }
        span a{
            text-decoration: none;
            color: aqua;
        }
        .add-card-box{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border: 2px dashed gray;
            background-color: white;
            padding: 20px 30px;
            border-radius: 8px;
        }
        .debit-card{
            margin-top: 50px;
            background-color: royalblue;
            display: flex;
            flex-direction: column;
            padding: 20px 30px;
            width: 275px;
            border-radius: 8px;
            color: white;
        }
        .debit-card-utility{
            margin-top: 20px;
        }
        .history{
            width: 275px;
            background-color: white;
            min-height: 300px;
            margin-top: 20px;
            border-radius: 8px;
            padding: 10px 20px;
        }
        th,td{
            font-size: 10px;
            border: 1px solid gray;
        }
    </style>
</head>
<body>
    <div class="main">
        <%
            if(user.getDebitCard()==null){
                %>
                    <div class="add-card-box">
                        <span>No card available</span>
                        <a href="debit"><button>Generate new debit card</button></a>
                    </div>
                <%
            }else{
                %>
                    <h1>Rs. <%=user.getDebitCard().getBalance()%></h1>

                    <div class="debit-card">
                        <span class="debit-card-number"><%=user.getDebitCard().getCardNumber()%></span>
                        <span class="expiration">Valid till <%=user.getDebitCard().getExpirationMonth()%>/<%=user.getDebitCard().getExpirationYear()%></span>
                        <span class="name"><%=user.getDebitCard().getCardHolderName()%></span>
                    </div>
                    <div class="debit-card-utility">
                        <a href="addmoney">
                            <button>Add money</button>
                        </a>
                        <a href="withdrawmoney">
                            <button>Withdraw money</button>
                        </a>
                    </div>
                    <div class="history">
                        <p>Transaction History</p>
                        <table>
                            <tr>
                                <th>Transaction ID</th>
                                <th>Transaction Type</th>
                                <th>Transaction Amount</th>
                            </tr>

                            <%
                                List<Transaction> transactionList = user.getDebitCard().getTransactionList();

                                if(!transactionList.isEmpty()){
                                    for(Transaction item:transactionList){
                                        %>
                                            <tr>
                                            <td><%=item.getId()%></td>
                                            <td><%=item.getTransactionType()%></td>
                                            <td><%=item.getTransactionAmount()%></td>
                                            </tr>
                                        <%
                                    }
                                }
                            %>

                        </table>
                    </div>
                <%
            }
        %>
    </div>
</body>
</html>