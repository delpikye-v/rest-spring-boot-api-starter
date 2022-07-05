package com.dp.spring.template.utils;

import com.dp.spring.template.common.WebApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class WebUtil {
    private static Logger logger = LoggerFactory.getLogger(WebUtil.class);
    public static final String UNKNOWN_IP_STRING = "unknown";
    private static final Pattern AJAX_REQUEST_PATTERN = Pattern.compile("XMLHttpRequest");
    private static final Pattern API_REQUEST_PATTERN = Pattern.compile("^/(api.*|\\d\\.\\d)/.*");

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return sra != null ? sra.getRequest() : null;
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return sra != null ? sra.getResponse() : null;
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public static String getLocaleString() {
        return getLocale().toString();
    }

    public static void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        LocaleResolver localeResolver;
        if (request != null) {
            localeResolver = RequestContextUtils.getLocaleResolver(request);
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
        }
    }

    public static <T> T getSpringBean(Class<T> clazz) {
        return getSpringBean(null, clazz);
    }

    public static <T> T getSpringBean(String beanName, Class<T> clazz) {
        WebApplicationContext wac = WebApplicationContextHolder.getWebApplicationContext();
        try {
            return beanName != null ? wac.getBean(beanName, clazz) : wac.getBean(clazz);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /*public static Configuration getConfigurationBean() {
        return getSpringBean(Configuration.class);
    }*/

    /**
     * Get a specific session by sessionId.
     *
     * @param sessionId
     * @return Session
     */
    /*public static Session getSessionById(String sessionId) {
        FindByIndexNameSessionRepository sessionRepository = getSpringBean(FindByIndexNameSessionRepository.class);

        return sessionRepository.getSession(sessionId);
    }*/

    /**
     * Get a specific session by sessionId.
     *
     * @param sessionId
     */
    /*public static void destroySessionById(String sessionId) {
        FindByIndexNameSessionRepository sessionRepository = getSpringBean(FindByIndexNameSessionRepository.class);

        sessionRepository.delete(sessionId);
    }*/

    /**
     * Get a specific session by loginId.
     *
     * @param loginId
     */
    /*public static void destroySessionByLoginId(String loginId) {
        FindByIndexNameSessionRepository sessionRepository = getSpringBean(FindByIndexNameSessionRepository.class);
        Map<String, Session> sessionMap = getSessionsByLoginId(loginId);
        for (Map.Entry<String, Session> sessionEntry : sessionMap.entrySet()) {
            String sessionId = sessionEntry.getKey();
            if(sessionRepository != null) {
                sessionRepository.delete(sessionId);
            }
        }
    }*/

    /**
     * Get sessions of a logged in user.
     *
     * @param loginId The loginId of the user.
     * @return Map of logged in user's sessions.
     */
    @SuppressWarnings("unchecked")
    /*public static Map<String, Session> getSessionsByLoginId(String loginId) {
        FindByIndexNameSessionRepository sessionRepository = getSpringBean(FindByIndexNameSessionRepository.class);

        return sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, loginId);
    }*/

    /**
     * Get securityContext of a specific session.
     *
     * @param session
     * @return
     */
    /*public static SecurityContext getSecurityContext(Session session) {
        return (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    }*/

    /**
     * Get a request parameter value, supporting inside a HsThread.
     *
     * @param name The request parameter name
     * @return String
     */
    public static String getRequestParameter(HttpServletRequest request, String name) {
        Map<String, String[]> requestParameterMap = null;
        if (request != null) {
            requestParameterMap = request.getParameterMap();
        }
        if (requestParameterMap != null) {
            String[] values = requestParameterMap.get(name);
            if (values != null) {
                if (values.length == 1) {
                    return values[0];
                }
            }
        }

        return null;
    }

    public static String getNewRowId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Checks if it is an Ajax request.
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xRequestedWith = request.getHeader("X-Requested-With");

        return xRequestedWith != null && AJAX_REQUEST_PATTERN.matcher(xRequestedWith).matches();
    }

    public static boolean isApiRequest(HttpServletRequest request) {
        return API_REQUEST_PATTERN.matcher(request.getRequestURI()).matches();
    }

    public static boolean isAuthenticated(HttpServletRequest request) {
        return request.getUserPrincipal() != null;
    }

    /**
     *
     * @param path The original path, if starting with /, the CDN url will be added to it.
     * @return The base CDN url if there is no param.
     */
    /*public static String getCdnUrl(String path) {
        String cdnUrl = getConfigurationBean().getAppCdnUrl();
        if (!path.isEmpty() && path.startsWith("/")) {
            cdnUrl = cdnUrl + path;
        }

        return cdnUrl;
    }*/

    /*public static String getCdnUrlForEmail(String url) {
        String cdnUrl = WebUtil.getCdnUrl(url);
        // Fallback to current app domain if there is no CDN
        if (!cdnUrl.startsWith("http")) {
            cdnUrl = WebUtil.getConfigurationBean().getBaseUrl() + url;
        }

        return cdnUrl;
    }*/

    /*public static String getWebUrl(String path) {
        String webUrl = getConfigurationBean().getBaseUrl();
        if (!path.isEmpty() && path.startsWith("/")) {
            webUrl = webUrl + path;
        }

        return webUrl;
    }*/

    /*public static String getApiUrl(String path) {
        String webUrl = getConfigurationBean().getAppApiUrl();
        if (!path.isEmpty() && path.startsWith("/")) {
            webUrl = webUrl + path;
        }

        return webUrl;
    }*/

    public static String getServerLocalIp() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        return ip;
    }

    public static String getServerPublicIp() {
        String ip = null;
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            ip = in.readLine();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return ip;
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN_IP_STRING.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP_STRING.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP_STRING.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP_STRING.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP_STRING.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
