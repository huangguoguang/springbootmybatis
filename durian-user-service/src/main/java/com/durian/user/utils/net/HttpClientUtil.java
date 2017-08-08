package com.durian.user.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;

public class HttpClientUtil {
	public static final String DEFAULT_CHARSET_ENCODING = "UTF-8";

	public static CloseableHttpClient createHttpsClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	public static String httpGet(URI uri, String charsetEncoding) {
		HttpClient httpClient = HttpClients.createDefault();
		return doHttpGet(uri, httpClient, charsetEncoding);
	}

	public static String httpGet(URI uri) {
		return httpGet(uri, "UTF-8");
	}

	public static String httpGet(String url, Map<String, String> params, String charsetEncoding) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}
		String paramString = URLEncodedUtils.format(nvps, charsetEncoding);
		return httpGet(URI.create(url + "?" + paramString), charsetEncoding);
	}

	public static String httpGet(String url, Map<String, String> params) {
		return httpGet(url, params, "UTF-8");
	}

	public static String sslHttpGet(URI uri, String charsetEncoding) {
		HttpClient httpClient = createHttpsClient();
		return doHttpGet(uri, httpClient, charsetEncoding);
	}

	public static String sslHttpGet(URI uri) {
		return sslHttpGet(uri, "UTF-8");
	}

	public static String sslHttpGet(String url, Map<String, String> params, String charsetEncoding) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}
		String paramString = URLEncodedUtils.format(nvps, charsetEncoding);
		return sslHttpGet(URI.create(url + "?" + paramString), charsetEncoding);
	}

	public static String sslHttpGet(String url, Map<String, String> params) {
		return sslHttpGet(url, params, "UTF-8");
	}

	public static String httpPost(URI uri, Map<String, String> params, String charsetEncoding) {
		HttpClient httpClient = HttpClients.createDefault();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}
		return doHttpPost(uri, httpClient, nvps, charsetEncoding);
	}

	public static String httpPost(URI uri, Map<String, String> params) {
		return httpPost(uri, params, "UTF-8");
	}

	public static String httpPost(String url, Map<String, String> params, String charsetEncoding) {
		URI uri = URI.create(url);
		return httpPost(uri, params, charsetEncoding);
	}

	public static String httpPost(String url, Map<String, String> params) {
		return httpPost(url, params, "UTF-8");
	}

	public static String sslHttpPost(URI uri, Map<String, String> params, String charsetEncoding) {
		HttpClient httpClient = createHttpsClient();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}
		return doHttpPost(uri, httpClient, nvps, charsetEncoding);
	}

	public static String sslHttpPost(String url, Map<String, String> params, String charsetEncoding) {
		URI uri = URI.create(url);
		return sslHttpPost(uri, params, charsetEncoding);
	}

	public static String sslHttpPost(String url, Map<String, String> params) {
		return sslHttpPost(url, params, "UTF-8");
	}

	public static String jsonPost(URI uri, String json, String charsetEncoding) {
		HttpClient httpClient = HttpClients.createDefault();
		return doJsonPost(uri, httpClient, json, charsetEncoding);
	}

	public static String jsonPost(URI uri, String json) {
		return jsonPost(uri, json, "UTF-8");
	}

	public static String jsonPost(String url, String json, String charsetEncoding) {
		URI uri = URI.create(url);
		return jsonPost(uri, json, charsetEncoding);
	}

	public static String jsonPost(String url, String json) {
		return jsonPost(url, json, "UTF-8");
	}

	public static String sslJsonPost(URI uri, String json, String charsetEncoding) {
		HttpClient httpClient = createHttpsClient();
		return doJsonPost(uri, httpClient, json, charsetEncoding);
	}

	public static String sslJsonPost(URI uri, String json) {
		return sslJsonPost(uri, json, "UTF-8");
	}

	public static String sslJsonPost(String url, String json, String charsetEncoding) {
		URI uri = URI.create(url);
		return sslJsonPost(uri, json, charsetEncoding);
	}

	public static String sslJsonPost(String url, String json) {
		return sslJsonPost(url, json, "UTF-8");
	}

	public static String xmlPost(URI uri, String xml, String charsetEncoding) {
		HttpClient httpClient = HttpClients.createDefault();
		return doXmlPost(uri, httpClient, xml, charsetEncoding);
	}

	public static String xmlPost(URI uri, String xml) {
		return jsonPost(uri, xml, "UTF-8");
	}

	public static String xmlPost(String url, String xml, String charsetEncoding) {
		URI uri = URI.create(url);
		return jsonPost(uri, xml, charsetEncoding);
	}

	public static String xmlPost(String url, String xml) {
		return jsonPost(url, xml, "UTF-8");
	}

	private static String doHttpGet(URI uri, HttpClient httpClient, String charsetEncoding) {
		HttpGet httpGet = new HttpGet(uri);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream in = httpResponse.getEntity().getContent();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, charsetEncoding));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			HttpClientUtils.closeQuietly(httpClient);
		}
	}

	private static String doHttpPost(URI uri, HttpClient httpClient, List<NameValuePair> nvps, String charsetEncoding) {
		HttpPost httpPost = new HttpPost(uri);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charsetEncoding));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			InputStream in = httpResponse.getEntity().getContent();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, charsetEncoding));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			HttpClientUtils.closeQuietly(httpClient);
		}
	}

	private static String doJsonPost(URI uri, HttpClient httpClient, String json, String charsetEncoding) {
		HttpPost httpPost = new HttpPost(uri);
		try {
			StringEntity stringEntity = new StringEntity(json, charsetEncoding);
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			InputStream in = httpResponse.getEntity().getContent();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, charsetEncoding));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			HttpClientUtils.closeQuietly(httpClient);
		}
	}

	private static String doXmlPost(URI uri, HttpClient httpClient, String json, String charsetEncoding) {
		HttpPost httpPost = new HttpPost(uri);
		try {
			StringEntity stringEntity = new StringEntity(json, charsetEncoding);
			stringEntity.setContentType("application/xml");
			httpPost.setEntity(stringEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			InputStream in = httpResponse.getEntity().getContent();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, charsetEncoding));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			HttpClientUtils.closeQuietly(httpClient);
		}
	}



	public static void main(String[] args) throws UnsupportedEncodingException {
		//CryptoUtils desCrypto = new CryptoUtils();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userID", "admin");
		paramMap.put("password", "founder");
		paramMap.put("version", "1.0");
		paramMap.put("ismi", "123");
		paramMap.put("serviceName", "userLogin");

		final HashMap<String, String> map = new HashMap<String, String>();
		//CryptoUtils.encryptParams(map, paramMap);
		map.put("access_token","g1-XgBh8GQVIpIj3u_GYj8d8I4hRbelGuPqVtQ-IkQvKuHHI-MrpAKX1lxnbAYVaKjmXBtteVHV_FmUDlPm_VtNxDo3JraayKcWjFoE76LQ");
		map.put("openid", "os712wgj6-95tMtV6Td4BLZsIEGg");
		
		String result = HttpClientUtil.sslHttpGet("https://api.weixin.qq.com/sns/auth", map);
		System.out.println(result);
//		Map<String,Object> resultMap = JSONObject.parseObject(result, Map.class);
//		System.out.println(resultMap.get("errcode"));
//		if(!"0".equals(resultMap.get("errcode").toString())){
//			System.err.println(resultMap.get("errcode"));
//		}

	}

	
}