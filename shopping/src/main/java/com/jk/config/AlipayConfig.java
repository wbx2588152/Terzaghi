package com.jk.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092000554682";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDRIRTMNnrUQdVvKNDNGBMw7mpO1/9MoCRkcllwHzodos9M6LycaAbeIfkm4c3/kvrJfp1E+6o+3ixB9PAYY4GmkCLEztWImbBNz+IIEfu9/SQGO/W0/QozfGhZVsVh01CUR/rSnTncdECuxorU+1wR/2XjQPTIympKgbDXCrbSpvoQ45d4jBIjBnMT6dsPitn7+cSnETA8One4t3YnErLsowMUE1pq9Im1WVTT0AzSD6UYM3AFEKqz7+vwpY48NKdEK3I/jl1y8sjAW37U0Ms8Zi2ATts8GSns+m7YAmh8iKGadP8qtqZz4YCKuBC/s6hUmB/mU2tFmBKo/XeZeeHPAgMBAAECggEBAMBLH9mKurSTeFLG//+mBHd4si2fNPPbSLtH3e9X91Mq0lyQoGVczfvQ100mUgmPt6QxFSFKG0fFeIKJW3/Bd4SVgjLfUL9TOibCYL808frHWimVVp++EHQ5D6YeQE5sROqjqQGiaoLILnXGv/+IxScTZTdqEerTHJs310hUZLkZ/7HZax0wZaP03bGpYKdMDlO/iPS4uViae9Q84a3XLmoxSI+fXTaRjOlrDiJFDlAMzm7cBRD9PxGYG+P5QiNVePtLAekmixKGuyk7LD+zq85Y9zp0CQCiRPBh66HMGDo9bTGk74GqKGavtYTheXDckpWQ0H7IXL14IHWKzcJ1XJECgYEA+Mo2Oni+mqGEw2BJ/5p+uSU+ZanpxvZHOrJY4AQQpTj7bTeM9ReaBvTIRNPO0gpW6PTbnxOM65cMHnGEMs5JpzZHqmyyLOJYC9q/JDa2xs4/98QMXCwhHy6oImMaDNvwte/CBV5DAyrAtT2Vy1d3MHH2+ivZ/So+MexgvGeyPUcCgYEA1zCf12mTd5BY/GaC0/tWioappQX91XxlSIr7a+CA1MHqxYJuBCLH4e3BN7TZbQXksW8ds4h+borbkDzF4nw2+2DEh7Aos+5mqAwGk+4vPkt0jXIoUkIWfqpKuEbgHcfh1CTvl68oWv2couyNJl9r0RNLhmBamUpWK2VT7tnuWzkCgYA8CVgSviaI3CdUmnzg8KBZ/3HSSLGAcNf5gyZNUM3PgTOzRG+AiaSCvzlA2rgTGamlZ8lD1wzvoTCsfApB1GQTtNaQetB4YlqK6cusaoOq8AShvq6Y45FOnAy4159msQCiduwkLX4QWpXMZsMqIb5Cc439Cu382N/PXlJtxLxPawKBgQDMMmLrOU7rPTmDwQ7ScwULDH/8jXPyWkmODfxIwM/flGfgpkUZfZupA0C/SMitkNMToDpR++rloLR66YBpy68s2QWFMJ9fJpGZU1gGlhWUimt+MV6buAS6i4O929udxue/tJD6ogasWU6Ct++1AwU/b4zSZapzHbwpD80WsQxi2QKBgQCV51AF7V9Io1VQOqQz6CeVao8QFUKjm1OxgTMmK7wjQ5x4gxWIUutzJjRMwpBkw7SyzvMleRYO00nuVVr/yIME37YI2tJjGQhx57L2g6XE8P+bvzrBFxS4gF/7PeN0pqwn190eUfIkVrFcYv9HojJ4ezVq5PFawhIH5VIFJ86LSg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp8b4fkKzR+9dTWnGl9FfzG73q1MseqS4kp1747KJXaQoiB6JxqNziXvoxprBuZNmQjTtWaNTT/2nE/rLqEtI8GYm47MtBTnbwj2BEuZkn5T1Gf52+BiQRKlcJ2Jchepp2YieBLcyWq3tbMtPFYhaZ1Pb8vUisyAkaj8iABrz5Z1M4OLml+2P/NPkSuwYR2x3kt6fZkY32DA7JNP2W2j64M3dDqd4KByLDtKA+fcKEv2JjSBZzCzwHP45eXFhuSZ7LdS+F2lpqp468Mossv6aJbN4PocuQGy4J0CWKU76ePuU4BPisHmwQlHQIG9Yy7WflHLj7+xoWRhKihK63WSaBwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8888/error.html";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8888/success.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:/wcj/1-8/实训二/workspace/workspaces-cloud/zhiFuRiZhi";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

