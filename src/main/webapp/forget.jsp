<!DOCTYPE html>
<html>
<center>

    <body>
        <form action="updatepass" method="POST">
            <input type="hidden" name="id" value=${user.id}>
            <input type="hidden" name="mailid" value=${user.mailid}>
            <input type="hidden" name="forgetpassword" value=${user.forgetPassword}>
            <label>Enter Your New Password</label><br>
            <input type="password" name="newpassword" placeholder="Enter your password" required><br><br>
            <label>Confirm Your New Password</label><br>
            <input type="password" name="confirmnewpassword" placeholder="Retype your password" required><br><br>
            <input type="submit" value="Change password">
        </form>
</center>


</body>

</html>