package com.embraer;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;

public class ScrapAutoLogin {

    public WebClient autoLogin(String loginUrl, String login, String password) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        HtmlPage page = client.getPage(loginUrl);
        System.out.println(page);
        // user: <input type="text" name="Ecom_UserID">
        // password: <input name="Ecom_Password" type="password">
        //<input type="hidden" name="target" value="https://www.techcare.embraer.com/irj/portal">
        //HtmlInput inputPassword = page.getFirstByXPath("//input[@name='Ecom_Password'][@type='password']");
        HtmlInput inputPassword = page.getFirstByXPath(
                "//body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr [@height='140px']" +
                        "/td [@valign='top']" +
                        "/div"+
                        "/table" +
                        "/form" +
                        "/tbody" +
                        "/tr [@height='5px']" +
                        "/td" +
                        "/tr" +
                        "/tr" +
                        "/td" +
                        "/label" +
                        "/td width='4px" +
                        "/td [@name='Ecom_User_ID']"
        );

        System.out.println(inputPassword);
        //The first preceding input that is not hidden
        //HtmlInput inputLogin = inputPassword.getFirstByXPath("//input[@type='text'][@name='Ecom_UserID']");
        //HtmlInput inputLogin = inputPassword.getFirstByXPath("//body/table/tbody/tr/td");
        //inputLogin.setValueAttribute(login);
        inputPassword.setValueAttribute(password);

        //get the enclosing form
        HtmlForm loginForm = inputPassword.getEnclosingForm() ;

        //submit the form
        page = client.getPage(loginForm.getWebRequest(null));

        //returns the cookie filled client :)
        return client;
    }
}
