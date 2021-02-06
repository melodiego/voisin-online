package br.com.voisinonline.config.security;

import br.com.voisinonline.config.security.auth.SecurityProperties;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class CookieService {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;
    private final SecurityProperties restSecProps;

    public CookieService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         SecurityProperties restSecProps) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.restSecProps = restSecProps;
    }

    public Cookie getCookie(String name) {
        return WebUtils.getCookie(httpServletRequest, name);
    }

    public void setCookie(String name, String value, int expiryInDays) {
        int expiresInSeconds = (int) TimeUnit.DAYS.toSeconds(expiryInDays);
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(restSecProps.getCookieProps().isSecure());
        cookie.setPath(restSecProps.getCookieProps().getPath());
        cookie.setDomain(restSecProps.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }

    public void setSecureCookie(String name, String value, int expiryInDays) {
        int expiresInSeconds = (int) TimeUnit.DAYS.toSeconds(expiryInDays);
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(restSecProps.getCookieProps().isHttpOnly());
        cookie.setSecure(restSecProps.getCookieProps().isSecure());
        cookie.setPath(restSecProps.getCookieProps().getPath());
        cookie.setDomain(restSecProps.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }

    public void setSecureCookie(String name, String value) {
        int expiresInMinutes = restSecProps.getCookieProps().getMaxAgeInMinutes();
        setSecureCookie(name, value, expiresInMinutes);
    }

    public void deleteSecureCookie(String name) {
        int expiresInSeconds = 0;
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(restSecProps.getCookieProps().isHttpOnly());
        cookie.setSecure(restSecProps.getCookieProps().isSecure());
        cookie.setPath(restSecProps.getCookieProps().getPath());
        cookie.setDomain(restSecProps.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }

    public void deleteCookie(String name) {
        int expiresInSeconds = 0;
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(restSecProps.getCookieProps().getPath());
        cookie.setDomain(restSecProps.getCookieProps().getDomain());
        cookie.setMaxAge(expiresInSeconds);
        httpServletResponse.addCookie(cookie);
    }
}
