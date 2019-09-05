package com.huifer.happy.common.utility.encryption;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 公钥私钥加密解密
 */
public class RSAEncryption {

    protected static final Logger log = LoggerFactory.getLogger(RSAEncryption.class);
    // 数字签名，密钥算法
    private static final String RSA_KEY_ALGORITHM = "RSA";
    // 数字签名签名/验证算法
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    // RSA密钥长度，RSA算法的默认密钥长度是1024密钥长度必须是64的倍数，在512到65536位之间
    private static final int KEY_SIZE = 1024;
    private static byte[] pubKey = null;
    private static byte[] priKey = null;

    private static void initKey() throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed("".getBytes());
        keygen.initialize(KEY_SIZE, secureRandom);
        KeyPair keyPair = keygen.genKeyPair();
        pubKey = keyPair.getPublic().getEncoded();
        priKey = keyPair.getPrivate().getEncoded();
        log.info("公钥={}", Base64.encodeBase64String(pubKey));
        log.info("私钥={}", Base64.encodeBase64String(priKey));
    }

    /**
     * RSA 加签
     *
     * @param data 待加签数据
     * @return byte[] 数字签名
     */
    public static String sign(byte[] data) throws Exception {
        if (pubKey == null || priKey == null) {
            initKey();
        }
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 验签
     *
     * @param data 待校验数据
     * @param sign 数字签名
     * @return boolean 成功返回true,失败返回false
     */
    public static boolean verify(byte[] data, byte[] sign) throws Exception {
        if (pubKey == null || priKey == null) {
            initKey();
        }
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubKey);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    /**
     * 公钥加密数据
     *
     * @param data 待加密数据
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPubKey(byte[] data) throws Exception {
        if (pubKey == null || priKey == null) {
            initKey();
        }
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密数据
     *
     * @param data 待加密数据
     * @return String 加密数据
     */
    public static String encryptByPubKey(String data) throws Exception {
        byte[] bytes = encryptByPubKey(data.getBytes());
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 私钥加密数据
     *
     * @param data 待加密数据
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPriKey(byte[] data) throws Exception {
        if (pubKey == null || priKey == null) {
            initKey();
        }
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密数据
     *
     * @param data 待加密数据
     * @return String 加密数据
     */
    public static String encryptByPriKey(String data) throws Exception {
        byte[] enSign = encryptByPriKey(data.getBytes());
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @return byte[] 原始数据
     */
    public static byte[] decryptByPubKey(byte[] data) throws Exception {
        if (pubKey == null || priKey == null) {
            initKey();
        }
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @return String 原始数据
     */
    public static String decryptByPubKey(String data) throws Exception {
        byte[] design = decryptByPubKey(Base64.decodeBase64(data));
        return new String(design);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @return byte[] 原始数据
     */
    public static byte[] decryptByPriKey(byte[] data) throws Exception {
        if (pubKey == null || priKey == null) {
            initKey();
        }
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @return String 原始数据
     */
    public static String decryptByPriKey(String data) throws Exception {
        byte[] design = decryptByPriKey(Base64.decodeBase64(data));
        return new String(design);
    }

}
