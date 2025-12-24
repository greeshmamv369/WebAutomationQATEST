package com.automation.tests;

import automation.Base.BaseClass;
import automation.pages.RegisterPage;
import org.testng.annotations.Test;

public class RegisterTests extends BaseClass {
    RegisterPage registerPage=new RegisterPage(driver);
    @Test(testName = "RegisterPage",description="New user onboarding")
    public void newUserOnboarding()
    {
        registerPage.newUserRegister();

    }
}
