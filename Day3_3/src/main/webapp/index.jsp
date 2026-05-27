<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>

<h2>Select Destinations</h2>

<form action="tour" method="post">

    <select name="place" size="3" multiple>
        <option value="agra">Agra</option>
        <option value="amritsar">Amritsar</option>
        <option value="delhi">Delhi</option>
    </select>

    <br><br>
    <input type="submit" value="Show Tour Plan">

</form>

</body>
</html>