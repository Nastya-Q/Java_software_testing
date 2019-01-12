package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void inputUserName (String username) {
        wd.get(app.getProperty("web.baseURL") + "/login_page.php");
        type(By.id("username"), username);
        click(By.cssSelector("input[type='submit']"));
    }

    public void inputUserPassword(String password) {
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }


}
