package com.xunjing.esshs.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class handlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String name = request.getHeader("name");
        if(name.isEmpty()){
            return false;
        }
        // 获取客户端IP地址（处理代理情况）
        String clientIp = getClientIp(request);

        // 获取用户代理信息
        String userAgent = request.getHeader("User-Agent");

        // 获取请求来源
        String referer = request.getHeader("Referer");

        // 获取请求方法和URI
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // 获取查询字符串
        String queryString = request.getQueryString();

        // 获取会话ID
        HttpSession session = request.getSession(false);
        String sessionId = session != null ? session.getId() : "No session";

        // 记录访问信息
        log.info("访问信息: IP={}, 用户代理={}, 来源={}, 方法={}, URI={}, 查询字符串={}, 会话ID={}",
                clientIp, userAgent, referer, method, uri, queryString, sessionId);
        return name.equals("esshs");
    }
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 如果是多级代理，取第一个IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }
}
