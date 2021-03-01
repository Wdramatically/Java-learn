<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>BMI</title>
</head>
<body>
    <fieldset><legend>计算BMI身体指数</legend>
        <form action="/bmi/bmi.do" method="post">
            身高：<input name="height" />cm
            体重：<input name="weight"/>kg
            <input type="submit" value="提交测试">
        </form>
    </fieldset>
</body>
</html>