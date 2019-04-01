package cn.stylefeng.star.modular.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求参数
 */
public class RequestUtil {
	private static Logger log = LoggerFactory.getLogger(RequestUtil.class);
	private static String charset = "UTF-8";
	
	public static void response(HttpServletResponse response, String resStr) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		OutputStream out = response.getOutputStream();
		out.write(resStr.getBytes(charset));
		out.flush();
		out.close();
	}
	


	public static String getServletName(HttpServletRequest request) {
		String url = request.getRequestURI();
		try {
			url = url.substring(url.lastIndexOf("/") + 1);
		} catch (Exception e) {
			log.error("获取请求名称失败:" + e);
		}
		return url;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了合作商软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向合作商的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	String REQUESTED_AJAX = "XMLHttpRequest";
	String HEADER_REQUESTED = "X-Requested-With";


	public static Map<String, Object> getReqMap(HttpServletRequest req) {
		return getReqMap(req, null);
	}

	public static Map<String, Object> getReqMap(HttpServletRequest req, Map<String, Object> map) {
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
		}

		Map paramMap = req.getParameterMap();
		if ((paramMap == null) || (paramMap.isEmpty())) {
			return map;
		}

		Set<String> keySet = paramMap.keySet();
		for (String key : keySet) {
			String[] values = (String[]) paramMap.get(key);
			if ((values == null) || (values.length == 0)){
				map.put(key, "");
			}	else if (values.length == 1){
				map.put(key, values[0]);
			}else {
				map.put(key, values);
			}

		}
		return map;
	}
	
	
	
	
	public boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader(this.HEADER_REQUESTED);

		return this.REQUESTED_AJAX.equalsIgnoreCase(requestType);
	}

	/**
	 * 获得请求内容
	 * 
	 * @return
	 */
	public static String getQueryBody(HttpServletRequest request) {
		try {
			BufferedReader br = request.getReader();

			String str, reqBody = "";
			while ((str = br.readLine()) != null) {
				reqBody += str;
			}
			return reqBody.trim();
		} catch (Exception e) {
			return "";
		}
	}
	

	
	
}
