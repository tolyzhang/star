package cn.stylefeng.star.modular.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * <p>UrlConnection通讯辅助类</p>
 * @Title: CallbackUtils.java
 * @Projectname: JAVA_JAR
 * @Package: com.yeepay.util 
 * @Copyright: Copyright (c)2014
 * @Company: YeePay
 * @version: 1.0
 * @Create: 2014年4月2日
 */
public class HttpUtils {
	/**
	 * 设置URL缓存时间为1小时
	 */
	static {
		System.setProperty("sun.net.inetaddr.ttl", "3600");
	}

	/**
	 * Logger for this class
	 */
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 默认字符编码
	 */
	public static final String DEFAULT_CHARSET = "UTF-8";

	public static final String HTTP_METHOD_POST = "POST";

	public static final String HTTP_METHOD_GET = "GET";

	/**
	 * 默认超时设置 (30秒)
	 */
	public static final int DEFAULT_TIMEOUT = 30000;

	/**
	 * 默认提交方式
	 */
	public static final String HTTP_METHOD_DEFAULT = "POST";

	public static final String HTTP_PREFIX = "http://";

	public static final String HTTPS_PREFIX = "https://";

	public static String httpRequest(String url, String method)
			throws Exception {
		return httpRequest(url, "", method, DEFAULT_CHARSET);
	}

	public static String httpRequest(String url, String queryString,
			String method) throws Exception {
		return httpRequest(url, queryString, method, DEFAULT_CHARSET);
	}

	public static String httpRequest(String url, Map params, String method)
			throws Exception {
		return httpRequest(url, params, method, DEFAULT_CHARSET);
	}

	public static String httpPost(String url, Map params) throws Exception {
		return httpRequest(url, params, HTTP_METHOD_POST, DEFAULT_CHARSET);
	}

	public static String httpPostGb23212(String url,Map params) throws Exception{
		return httpRequest(url, params, HTTP_METHOD_POST, "GB2312");

	}

	public static String httpPostGBK(String url, String queryString)
			throws Exception {
		return httpRequest(url, queryString, HTTP_METHOD_POST, "GBK");
	}

	public static String httpPost(String url, String queryString)
			throws Exception {
		return httpRequest(url, queryString, HTTP_METHOD_POST, DEFAULT_CHARSET);
	}

	public static String httpGet(String url, Map params) throws Exception {
		return httpRequest(url, params, HTTP_METHOD_GET, DEFAULT_CHARSET);
	}

	public static String httpGet(String url, String queryString)
			throws Exception {
		return httpRequest(url, queryString, HTTP_METHOD_GET, DEFAULT_CHARSET);
	}

	/**
	 * 以建立HttpURLConnection方式发送请求
	 *
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方式
	 * @param charSet
	 * @return 通讯失败返回null, 否则返回服务端输出
	 * @throws Exception
	 */
	public static String httpRequest(String url, Map params, String method,
			String charSet,Map<String,Object> requestHead) throws Exception {
		String queryString = parseQueryString(params, charSet);
		return httpRequest(url, queryString, method, charSet,requestHead);
	}
	/**
	 * 以建立HttpURLConnection方式发送请求
	 *
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方式
	 * @param charSet
	 * @return 通讯失败返回null, 否则返回服务端输出
	 * @throws Exception
	 */
	public static String httpRequest(String url, Map params, String method,
			String charSet) throws Exception {
		String queryString = parseQueryString(params, charSet);
		return httpRequest(url, queryString, method, charSet);
	}


	/**
	 * 以建立HttpURLConnection方式发送请求
	 *
	 * @param targetUrl
	 *            请求地址
	 * @param queryString
	 *            请求参数
	 * @param method
	 *            请求方式
	 * @param charSet
	 * @return 通讯失败返回null, 否则返回服务端输出
	 * @throws Exception
	 */
	public static String httpRequest(String targetUrl, String queryString,
			String method, String charSet) throws Exception {
		Map<String,Object> requestHead = new HashMap<>();
		requestHead.put("Content-Type","application/json");
		logger.info("头信息:{}",requestHead);
		return httpRequest(targetUrl,queryString,method,charSet,requestHead);
	}

	/**
	 * 以建立HttpURLConnection方式发送请求
	 *
	 * @param targetUrl
	 *            请求地址
	 * @param queryString
	 *            请求参数
	 * @param method
	 *            请求方式
	 * @param charSet
	 * @return 通讯失败返回null, 否则返回服务端输出
	 * @throws Exception
	 */


	public static String httpRequest(String targetUrl, String queryString,String method, String charSet,Map<String,Object> requestHead) throws Exception {
		logger.info("请求地址字符串targeURL[{}],参数[{}],提交方式[{}],编码格式[{}]",targetUrl,queryString,method,charSet);
		HttpURLConnection urlConn = null;
		URL destURL = null;
		boolean httpsFlag = false;
		if (targetUrl == null || targetUrl.trim().length() == 0) {
			throw new IllegalArgumentException("invalid targetUrl : "
					+ targetUrl);
		}
		targetUrl = targetUrl.trim();

		// if(targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)){
		// throw new IllegalArgumentException("unsupport protocal : https");
		// }

		if (targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)) {
			httpsFlag = true;
		} else if (!targetUrl.toLowerCase().startsWith(HTTP_PREFIX)) {
			targetUrl = HTTP_PREFIX + targetUrl;
		}

		// if(!targetUrl.toLowerCase().startsWith(HTTP_PREFIX) &&
		// !targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)){
		// targetUrl = HTTP_PREFIX+targetUrl;
		// }
		if (queryString != null) {
			queryString = queryString.trim();
		}
		if (method == null
				|| !(method.equals(HTTP_METHOD_POST) || method
						.equals(HTTP_METHOD_GET))) {
			throw new IllegalArgumentException("invalid http method : "
					+ method);
		}

		String baseUrl = "";
		String params = "";
		String fullUrl = "";

		int index = targetUrl.indexOf("?");
		if (index != -1) {
			baseUrl = targetUrl.substring(0, index);
			params = targetUrl.substring(index + 1);
		} else {
			baseUrl = targetUrl;
		}
		if (queryString != null && queryString.trim().length() != 0) {
			if (params.trim().length() > 0) {
				params += "&" + queryString;
			} else {
				params += queryString;
			}
		}

		//params = URLEncoder.encode(params, charSet);

		fullUrl = baseUrl + (params.trim().length() == 0 ? "" : ("?" + params));
		logger.info("fullUrl:" + fullUrl);
		System.out.println("fullUrl:" + fullUrl);
		StringBuffer result = new StringBuffer(2000);
		try {
			if (method.equals(HTTP_METHOD_POST)) {
				destURL = new URL(baseUrl);
//				destURL = new URL(null,baseUrl,new URLSHandler);
			} else {
				destURL = new URL(fullUrl);
//				destURL = new URL(null,fullUrl,new sun.net.www.protocol.https.Handler());
			}
			if (httpsFlag) {
				urlConn = (HttpsURLConnection) destURL.openConnection();
			} else {
				urlConn = (HttpURLConnection) destURL.openConnection();
			}
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=" + charSet);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setAllowUserInteraction(false);
			logger.info(method);
			urlConn.setUseCaches(false);
			urlConn.setRequestMethod(method);
			urlConn.setConnectTimeout(DEFAULT_TIMEOUT);
			urlConn.setReadTimeout(DEFAULT_TIMEOUT);
			urlConn.setRequestProperty("User-Agent", "Mozilla/4.7 [en] (Win98; I)");
			if(requestHead != null && !requestHead.isEmpty()){
				for (String key : requestHead.keySet()) {
					urlConn.setRequestProperty(key, (String)requestHead.get(key));
				}
			}

			if (method.equals(HTTP_METHOD_POST)) {
				OutputStream os = urlConn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, charSet);
				osw.write(params);
				osw.flush();
				osw.close();
			}
			logger.info("请求地址字符串targeURL[{}]",targetUrl);
			BufferedInputStream is = new BufferedInputStream(urlConn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,charSet));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				result.append(temp);
				result.append("\n");
			}
			String resultStr = result.toString();
			//解决PHP返回<feff><feff> BOM的问题
			if(resultStr.startsWith("\uFEFF")){
				resultStr = resultStr.replace("\uFEFF", "");
			}
			if(resultStr.endsWith("\n")){
				resultStr = resultStr.substring(0, resultStr.length() - "\n".length());
			}
			int responseCode = urlConn.getResponseCode();
			logger.info("------------------ResponseCode[" + responseCode
					+ "],and \r\n targetUrl:[" + targetUrl + "?" + queryString + "]"
					+ " and \r\n result:[" + resultStr + "]");
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return null;
			}
			return resultStr;
		} catch (Exception e) {
			logger.error("connection error : " + e.getMessage(), e);
			throw e;

		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
	}

	/**
	 * 根据查询返回结果分割获得Map
	 *
	 * @param
	 * @return
	 */
	public static Map queryString(String queryResult, String splitChar) {

		String[] keyValuePairs = queryResult.split(splitChar);
		Map<String, String> map = new HashMap<String, String>();
		for (String keyValue : keyValuePairs) {
			if (keyValue.indexOf("=") == -1) {
				continue;
			}
			String[] args = keyValue.split("=");
			if (args.length == 2) {
				map.put(args[0], args[1]);
			}
			if (args.length == 1) {
				map.put(args[0], "");
			}
		}

		return map;
	}

	/**
	 * 把参数map转换成URL
	 *
	 * @param params
	 * @param charSet
	 * @return
	 */
	public static String parseQueryString(Map params, String charSet) {
		if (null == params || params.keySet().size() == 0) {
			return "";
		}
		StringBuffer queryString = new StringBuffer(2000);
		for (Iterator i = params.keySet().iterator(); i.hasNext();) {
			String key = String.valueOf(i.next());
			Object obj = params.get(key);
			String value = "";
			if (obj != null) {
				value = obj.toString();
			}
			try {
				value = URLEncoder.encode(value, charSet);
			} catch (UnsupportedEncodingException ex) {
				logger.info("encode url error: " + ex.getMessage());
			}
			queryString.append(key);
			queryString.append("=");
			queryString.append(value);
			queryString.append("&");
		}
		String result = queryString.toString();
		if (result.endsWith("&")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public static String parseUrl(String targetUrl, String queryString) {
		if (targetUrl == null || targetUrl.trim().length() == 0) {
			throw new IllegalArgumentException("invalid targetUrl : "
					+ targetUrl);
		}
		targetUrl = targetUrl.trim();
		if (!targetUrl.toLowerCase().startsWith(HTTP_PREFIX)
				&& !targetUrl.toLowerCase().startsWith(HTTPS_PREFIX)) {
			targetUrl = HTTP_PREFIX + targetUrl;
		}

		if (queryString != null) {
			queryString = queryString.trim();
		}
		String baseUrl = "";
		String paramString = "";
		String fullUrl = "";
		int index = targetUrl.indexOf("?");
		if (index != -1) {
			baseUrl = targetUrl.substring(0, index);
			paramString = targetUrl.substring(index + 1);
		} else {
			baseUrl = targetUrl;
		}
		if (queryString != null && queryString.trim().length() != 0) {
			if (paramString.trim().length() > 0) {
				paramString += "&" + queryString;
			} else {
				paramString += queryString;
			}
		}
		fullUrl = baseUrl
				+ (paramString.trim().length() == 0 ? "" : ("?" + paramString));
		return fullUrl;
	}

	public static String parseUrl(String targetUrl, Map params, String charSet) {
		String queryString = parseQueryString(params, charSet);
		return parseUrl(targetUrl, queryString);
	}

	public static Map parseQueryString(String queryString) {
		if (queryString == null) {
			throw new IllegalArgumentException("queryString must be specified");
		}

		int index = queryString.indexOf("?");
		if (index > 0) {
			queryString = queryString.substring(index + 1);
		}

		String[] keyValuePairs = queryString.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String keyValue : keyValuePairs) {
			if (keyValue.indexOf("=") == -1) {
				continue;
			}
			String[] args = keyValue.split("=");
			if (args.length == 2) {
				map.put(args[0], args[1]);
			}
			if (args.length == 1) {
				map.put(args[0], "");
			}
		}
		return map;
	}

	public static String parseUrl(String queryString) {
		if (queryString == null) {
			throw new IllegalArgumentException("queryString must be specified");
		}

		int index = queryString.indexOf("?");
		String targetUrl = null;
		if (index > 0) {
			targetUrl = queryString.substring(0, index);
		} else {
			targetUrl = queryString;
		}
		return targetUrl;
	}

	/**
	 * 把参数map转换成URL
	 *
	 * @param params
	 * @param separator
	 *            分隔符
	 * @param charSet
	 * @return
	 */
	public static String parseQueryString(Map params, String separator,
			String charSet) {
		if (null == params || params.keySet().size() == 0) {
			return "";
		}
		StringBuffer queryString = new StringBuffer(2000);
		for (Iterator i = params.keySet().iterator(); i.hasNext();) {
			String key = String.valueOf(i.next());
			Object obj = params.get(key);
			String value = "";
			if (obj != null) {
				value = obj.toString();
			}
			try {
				value = URLEncoder.encode(value, charSet);
			} catch (UnsupportedEncodingException ex) {
				logger.info("encode params error: " + ex.getMessage());
			}
			queryString.append(key);
			queryString.append("=");
			queryString.append(value);
			queryString.append(separator);
		}
		String result = queryString.toString();
		if (result.endsWith(separator)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/*private static void trustAllHttpsCertificates() {
		try {
			// Create a trust manager that does not validate certificate chains:
			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
			javax.net.ssl.TrustManager tm = new MyTrustManager();
			trustAllCerts[0] = tm;
			javax.net.ssl.SSLContext sc;
			sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.debug("",e);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			logger.debug("",e);
		} catch (Exception e){
			e.printStackTrace();
			logger.debug("",e);
		}
	}*/

	public static void main(String[] args) throws Exception {

	}

	/**
	 * 接口调用POST
	 * @param path
	 * @param param:json字符串格式参数
	 */
	public static String HttpDoPost (String path,String param) {
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = bf.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
			bf.close();
			connection.disconnect();
			return sb.toString();
		} catch (Exception e) {
			//log.info("{}接口调用失败(POST)！",path);
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 接口调用GET
	 * @param path
	 */
	public static String HttpDoGet(String path) {
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			connection.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
