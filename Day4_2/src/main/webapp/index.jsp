<%@ page import="java.util.*, java.util.ArrayList" %>
<%

    List<String> cart = (List<String>) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList<String>();
        session.setAttribute("cart", cart);
    }

    String addItem = request.getParameter("add");
    if (addItem != null && !cart.contains(addItem)) {
        cart.add(addItem);
    }

    String removeItem = request.getParameter("remove");
    if (removeItem != null) {
        cart.remove(removeItem);
    }

    String clearCart = request.getParameter("clear");
    if ("true".equals(clearCart)) {
        cart.clear();
    }
%>
<html>
<body>

<a href="Toys.jsp">[Shop For Toys]</a>

<br><br>

<%
    String[] cds = {"Dhurandhar-2", "Superman", "Life of Pi"};
    for (String cd : cds) {
%>
    <%= cd %>
    &nbsp;&nbsp;
    <a href="index.jsp?add=<%= java.net.URLEncoder.encode(cd, "UTF-8") %>">Add to Cart</a>

    <br><br>
<%
    }
%>

<hr>

<b>Shopping Cart Contents</b>
&nbsp;&nbsp; <a href="index.jsp?clear=true">[Clear Cart]</a>
<ul>
<%
    if (cart.isEmpty()) {
%>
    <li>Cart is empty</li>
<%
    } else {
        for (String item : cart) {
%>
    <li><%= item %> &nbsp; <a href="index.jsp?remove=<%= java.net.URLEncoder.encode(item, "UTF-8") %>">Remove from Cart</a></li>
<%
        }
    }
%>
</ul>

</body>
</html>
