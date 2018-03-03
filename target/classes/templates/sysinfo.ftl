<!DOCTYPE html>
<!-- url: /sysinfo/{info}/ -->
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Server Info</title>

</head>
<body>
    <#if infoMap?exists>
        <table border="1" align="center">
            <thead align="center"><h1>System Information</h1></thead>
            <#list infoMap?keys as key>
                <#if !key?contains("class.path")>
                    <tr>
                        <td align="left">${key} = ${infoMap[key]}</td>
                    </tr>
                </#if>
            </#list>
        </table>
    </#if>

<h1></h1>

</body>
</html>
