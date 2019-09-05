package com.huifer.happy.common.utility.encryption;


import org.apache.commons.codec.binary.Base64;
import org.junit.Test;


public class RSAEncryptionTest {

    @Test
    public void rsaTest() throws Exception {
        String data = "这是一段被加密的数据>.<";
        // 公钥加密
        String pubKeyStr = RSAEncryption.encryptByPubKey(data);
        System.out.println("公钥加密后 == " + pubKeyStr);
        // 私钥解密
        String priKeyStr = RSAEncryption.decryptByPriKey(pubKeyStr);
        System.out.println("私钥解密后 == " + priKeyStr);
        // 数字签名验证
        String sign1 = "sign1";
        String sign = RSAEncryption.sign(sign1.getBytes());
        System.out.println("数字签名结果 == " + sign);
        // 数字签名验证
        boolean verify = RSAEncryption.verify(sign1.getBytes(), Base64.decodeBase64(sign));
        System.out.println(verify);
    }
}