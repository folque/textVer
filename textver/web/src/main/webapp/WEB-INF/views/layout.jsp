<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body bgcolor="#A9A3A3">
<table border="0" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td height="30" colspan="3"><tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td height="250" valign="top" bgcolor="#FFFFCC"><tiles:insertAttribute name="menu" /></td>
        <td width="350" valign="top"><tiles:insertAttribute name="body" /></td>
        <td valign="top" bgcolor="#FFFFCC"><tiles:insertAttribute name="submenu"/></td>
    </tr>
    <tr>
        <td height="30" colspan="3" bgcolor="#FFFFCC"><tiles:insertAttribute name="footer" />
        </td>
    </tr>
</table>
</body>
</html>