package com.sunseeker.mall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sunseeker.mall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private  String app_id = "2021000117667969";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMGBCEPcJjdI4riehiZXPjnIgB1v665oEJcR6eUxMzGAuqvoyFQ94qPY1uWnvp56r0HOOMZSd1CnesLPY8nf3B77IUaUecfF0W4cqFTw7v5gqmeo75n2RMpv13w5q7Qb3VCs6pbbPZE67TE3M8dG79FTMyCbNkHp7L0nwRisN4oC6ACbqf3k+yGry+F4m3YrE8mcTTOUCNaZQZTN682ZQxPM1d4GckbG3PJqAk9rzmYEhROKw4WK9fnewUT1kDvSQftQS1B0gGYCZy/oO+3iQA7eL2QzDL+hdmOLB03Au/0ipabDbAT/XTc1iIJ7BMT1UwjDmPDyjkwGqkhWaAEnd5AgMBAAECggEAHvhS7mkYyBPEOivKi7YR4IeCKaX1uiuq6wNu2WFUJT9zlNN7DRrryjrBQVwPeK4WRND+HNNf3afqvM1TwiLLLkxm+cwsYJO9oIlZ1A6eJrqcMCF36gcqjSd9cTR0yGDILoitFxeBYsPPKipqlzrGLny5TJ6IYeHDCRwHAXnDrK6n5HHjgeuGS/YEHPCj7+Vzb8HF8qeS+zKDdCnipboxFXDgXBb18UMchFbEc2QBikaUFX/Zf08qkE55DOzMc0fQ/zdAKmxPMSKq0kmvPRVq1FH3aitT8QdNfIktY6MaFKWXNWFDuBO24dPaKqDH9f0XISFw+WthSqw/KmVxYmAA4QKBgQDci92RipvKDKsqNf33GVuJm42VbPEM6JfoSwMikyacfYRrGtCQ54UP6rteXrfRwJ8eXaVg1yrXfeA+6CpBAMgauUZfj7ZhOg0wyhy9aRk5vSQQJjHP/bcUZn8Ghb+IQgSDDgnNx7H3c0igzQqxAXz+HdX+TbIi3L1mWdPL7owi/wKBgQCinVjUn/MTJ7EpTwxpMJeQbMtXm8Jgi/v2CtYf9/8uRdmkVHYAyGfmmHOVR3ByvZJDCowaV9ZzYqQ1PZHqWfVinUZeb9/JTKUE1r5ofq5MnpjY9Z7pR+n7cGlWlsD0KJHuocGqiakt6l310GgDRaS7eA5Csl+FJZlju47DSGr9hwKBgEl7oJt5n2a2yeEKlE/cdw9mcMo0viwa42JWe/jtJpRFjfVRveRJ/5/KChvrWzvb/ihT82JMzs11911+ZXW7GPEugjAc5huHCBYVgXXqqqj+SCHJWBeA5Zs6e2Bk9+gidvZjmwihXL6h/rT1HHbVlBvnPQG6SXwsKD8N9u+fz7lpAoGAYwttaIxHtFoPqPZxu7wSBLCPJbxEJyP8Aj9VbkFR1vBKgLHE8fMefbBLbhgGIIzggh7xeLYZc0wYcikfL9s8/6SFkM2ZQ3WAaTMW5HSMWzSaKkNoV/QqSrCMKGZH/i6bu/ogCGwydDn0ot0eDSglDt4v6WXVkjZ1d+5sJPt91w8CgYAv1a40hNyorErDhXhA6ER7khGW2lEWHvPXfy7oahuyEqotvjDqZbrm+XZDYMhWGH2JhPWjY5tvnKRY+Q0ct/yyBGJ5PbgztxKUbW1oRfPUouhEmrpITiPw36xaLd3i6gSPGTgEOQN9s3LJrLTefpMzRVqqUEzQeQwvVGbJKjylWA==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArgjpLPeU7P45jS+uKKFpsMtHfvoiktdgTAfx6NtE/rhgmHsEtqXO5i2E6RPnAJC7HEYaqIC/W69m5VRCnj5i54pUrue9J5Cv5IOO8mick47A0IIvYFXTDCgGYQrRkV7aYhtb3c50Qy9IM1YeSS8lM2O+xfNg/54GsHW8kEMMZ0mN1IlPlaTSvKg8Qq6y/0BqYwcOj8igOGO3/Q4SXZ7zcWQKTMcsDxtGOV8WZ586A7r1GBZxekjybk2DcaeRdfz0ZYELVq1srlW0gBx/Qa9+pgCnHNUiCvcdUSuT8fTr6xaACo+yam6R3mBhj9cunDtDwugcNTLgLH2EKyX/E8DcHQIDAQAB";

    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url="https://xdu-mall.store:88/api/order/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url="https://xdu-mall.shop/space";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                +"\"timeout_express\":\"1m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面

        return result;

    }
}
