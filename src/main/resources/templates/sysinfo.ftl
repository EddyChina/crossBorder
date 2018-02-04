<!DOCTYPE html>
<!-- url: /sysinfo/{info}/ -->
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Server Info</title>
    <style>
        ::-moz-selection {
            background: #b3d4fc;
            text-shadow: none;
        }

        ::selection {
            background: #b3d4fc;
            text-shadow: none;
        }

        html {
            padding: 30px 10px;
            font-size: 20px;
            line-height: 1.4;
            color: #737373;
            background: #f0f0f0;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
        }

        body {
            max-width: 1550px;
            _width: 550px;
            padding: 30px 20px 50px;
            border: 1px solid #b3b3b3;
            border-radius: 4px;
            margin: 0 auto;
            box-shadow: 0 1px 10px #a7a7a7, inset 0 1px 0 #fff;
            background: #fcfcfc;
        }

        h1 {
            margin: 0 10px;
            font-size: 50px;
            text-align: center;
        }

        h1 span {
            color: #bbb;
        }

        h3 {
            margin: 1.5em 0 0.5em;
        }

        p {
            margin: 1em 0;
        }

        ul {
            padding: 0 0 0 40px;
            margin: 1em 0;
        }

    </style>
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
