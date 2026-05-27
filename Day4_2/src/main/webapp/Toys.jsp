<%@ page import="java.util.*, java.util.ArrayList" %>
<%
    // Initialize cart in session if not present
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

    // Handle Clear Cart
    String clearCart = request.getParameter("clear");
    if ("true".equals(clearCart)) {
        cart.clear();
    }
%>
<html>
<body>

<a href="index.jsp">[Shop For CDs]</a>

<br><br>

<%
    String[] toys = {"Barbie Doll", "Play House", "Matleno"};
    for (String toy : toys) {
%>
    <%= toy %>
    &nbsp;&nbsp;
    <a href="Toys.jsp?add=<%= java.net.URLEncoder.encode(toy, "UTF-8") %>">Add to Cart</a>

    <br><br>
<%
    }
%>

<hr>

<b>Shopping Cart Contents</b>
&nbsp;&nbsp; <a href="Toys.jsp?clear=true">[Clear Cart]</a>
<ul>
<%
    if (cart.isEmpty()) {
%>
    <li>Cart is empty</li>
<%
    } else {
        for (String item : cart) {
%>
    <li><%= item %> &nbsp; <a href="Toys.jsp?remove=<%= java.net.URLEncoder.encode(item, "UTF-8") %>">Remove from Cart</a></li>
<%
        }
    }
%>
</ul>

</body>
</html>
