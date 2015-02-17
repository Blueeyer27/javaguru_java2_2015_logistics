<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Add new cargo</title>
</head>
<body>
<div align="center"><img src="../images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../index.html">BACK</a></div>
<hr />
<div align="center">
    <form method="post" action="../java2/cargoSearchResult">
        Vehicle type:
        <select name="type">
            <option value="platform" selected>&nbsp;platform
            <option value="refrigerator" >&nbsp;refrigerator
            <option value="tilt" >&nbsp;tilt
        </select><br/>
        <br/>
        Weight between <input type="text" name="weightFrom" /> and <input type="text" name="weightTo" /><br/>
        <br/>
        Load date between <input type="date" name="loadDateFrom" /> and <input type="date" name="loadDateTo" /><br/>
        <br/>
        Unload date between <input type="date" name="unloadDateFrom" /> and <input type="date" name="unloadDateTo" /><br/>
        <br/>
        <input type="submit" value="Search" />
    </form>
</div>
</body>
</html>

