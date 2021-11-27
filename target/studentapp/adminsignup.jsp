<!DOCTYPE html>
<html>

<head>
    <title>Signup as admin</title>
</head>
<center>   
<body>
    <form action="register" method="POST">
        <input type="hidden" name ="decider" value="1"><br><br>
        <label>Mailid</label><br>
        <input type="text" name ="mailid" placeholder="Enter Mail Id"><br><br>
        <label>Password</label><br>
        <input type="password" name ="password" placeholder="Enter password"><br><br>
        <label>Confirm Password</label><br>
        <input type="password" name ="confirmPassword" placeholder="Retype-password"><br><br>
        <label>what is name of your first school? </label><br>
        <input type="text" name="forgetPassword" placeholder="Enter your answer"><br><br>

        <input type="submit" value="signup"><br><br>
        <input type="submit" value="login" formaction="index.html">

    </form>
</body>
</center>
</html>