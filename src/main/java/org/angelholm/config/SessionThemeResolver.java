package org.angelholm.config;

import org.zkoss.web.theme.ThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionThemeResolver /*implements ThemeResolver */{
  /*  @Override
    public String getTheme(HttpServletRequest request) {
        HttpSession sess = request.getSession();
        if (sess != null) {
            return sess.getAttribute("mytheme");
        }
    }

    @Override
    public void setTheme(HttpServletRequest request, HttpServletResponse response, String themeName) {
        HttpSession sess = request.getSession();
        if (sess != null) {
            sess.setAttribute("mytheme", themeName);
        }
    }*/
}