package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserActionsHelper extends HelperBase {

    public UserActionsHelper(ApplicationManager app) {
        super(app);
    }

    public void uiLogin (String userName, String userPassword) {
        inputUserName(userName);
        inputUserPassword(userPassword);
    }

    public void resetPasswordForUser (int userId) {
        wd.get(app.getProperty("web.baseURL") + "manage_user_edit_page.php?user_id=" + userId);
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void inputUserName (String user) {
        wd.get(app.getProperty("web.baseURL") + "/login_page.php");
        type(By.id("username"), user);
        click(By.cssSelector("input[type='submit']"));
    }

    public void inputUserPassword(String password) {
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }


}
