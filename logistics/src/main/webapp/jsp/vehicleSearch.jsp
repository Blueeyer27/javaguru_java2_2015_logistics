<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Find my vehicle</title>
</head>
<body>
<div align="center"><img src="../images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../index.html">BACK</a></div>
<hr />
<div align="center">
    <form method="post" action="../vehicleSearchResult">
        Vehicle type:
        <select name="type">
            <option value="platform" selected>&nbsp;platform
            <option value="refrigerator" >&nbsp;refrigerator
            <option value="tilt" >&nbsp;tilt
        </select><br/>
        <br/>
        Capacity between <input type="text" name="capacityFrom" /> and <input type="text" name="capacityTo" /><br/>
        <br/>
        <input type="submit" value="Search" />
    </form>
</div>
</body>
</html>

