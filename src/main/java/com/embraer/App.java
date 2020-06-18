package com.embraer;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class App 
{
    public static void main( String[] args )
    {
        SearchWithQuery searchWithQuery = new SearchWithQuery();
        ScrapAutoLogin scrapAutoLogin = new ScrapAutoLogin();

        String baseUrl = "https://news.ycombinator.com" ;
        String loginUrl = baseUrl + "/login?goto=news" ;
        String login = "";
        String password = "" ;

        String mtcUrl = "https://www.techcare.embraer.com/irj/portal";

        try {
            System.out.println("Starting autoLogin on " + mtcUrl);
            WebClient client = scrapAutoLogin.autoLogin(mtcUrl, login, password);
            HtmlPage page = client.getPage(baseUrl) ;

            HtmlAnchor logoutLink = page.getFirstByXPath(String.format("//a[@href='user?id=%s']", login)) ;
            if(logoutLink != null ){
                System.out.println("Successfuly logged in !");
                // printing the cookies
                for(Cookie cookie : client.getCookieManager().getCookies()){
                    System.out.println(cookie.toString());
                }
            }else{
                System.err.println("Wrong credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
