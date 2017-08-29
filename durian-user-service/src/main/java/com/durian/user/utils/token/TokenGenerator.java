package com.durian.user.utils.token;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 *
 */
@Service
@Configuration
public class TokenGenerator {

    private static Logger logger = LoggerFactory.getLogger(TokenGenerator.class);

    public static final String KEY_ALGORITHM = "DES";
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";

    @Value("${token.stringKey}")
    private  String stringKey ;//="安全第一";

    private  Integer expires;  //过期时间

    private  SecretKey deskey; // 密钥

    @Value("${token.owmuser}")
    private  String owmuser ;//= "guess";          //

    /**
     * 生成token
     * @param userId
     * @return
     * @throws Exception
     */
    public  String generatorToken(String mobile ,String userId,String type,Integer expires) throws Exception {
        if(deskey==null){
            init();
        }
        Token token = new Token();
        token.setMobile(mobile);
        token.setUserId(userId);
        token.setType(type);
        token.setCreateDate(new Date().getTime());
        token.setOwmuser(owmuser);
        token.setExpires(expires);
        return encrytor(JSONObject.toJSONString(token));
    }

    /**
     * 验证token是否有效
     * @param tokenString
     * @return
     */
    public  Token validateToken(String tokenString) {
        try {
        	if(StringUtils.isBlank(tokenString)) {
        		return null ;
        	}
            String str = decryptor(tokenString);
            Token token = JSONObject.parseObject(str, Token.class);
            if(token ==null){
                return null ;
            }
            //boolean expires = (token.getExpires()== 0 || ( ((new Date().getTime() - token.getCreateDate())/1000) > token.getExpires()));
            if ( ((new Date().getTime() - token.getCreateDate()) / 1000 ) >token.getExpires()  && StringUtils.equals(token.getOwmuser(), owmuser)) {
                return null;
            }
            return token ;
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 初始化加密文字
     * @throws Exception
     */
    public   void init() throws Exception {
        byte[] key = stringKey.getBytes();
        // 创建一个空的8位字节数组（默认值为0）
        byte[] encodedKey = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < key.length && i < encodedKey.length; i++) {
            encodedKey[i] = key[i];
        }
        deskey = new SecretKeySpec(encodedKey, 0, encodedKey.length, KEY_ALGORITHM);
    }

    /**
     * 加密
     * @param str 明文
     * @return 密文,base64编码
     */
    public  String encrytor(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] target = cipher.doFinal(str.getBytes());
        return Base64.encodeBase64URLSafeString(target);
    }

    /**
     * 解密
     * @param str 密文,base64编码
     * @return 明文
     * @throws Exception
     */
    public  String decryptor(String str) throws Exception {
        byte[] src = Base64.decodeBase64(str);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        return new String(cipher.doFinal(src));
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }


    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public static void main(String[] args) throws Exception {
        TokenGenerator tokenGenerator = new TokenGenerator();
        tokenGenerator.setStringKey("安全第一");
        //tokenGenerator.setIssuer("luckymoney");
        tokenGenerator.setExpires(0);
        tokenGenerator.init();
        String tokenString = tokenGenerator.generatorToken("18627038327","18627038327","pc",60 * 2);
        System.out.println(tokenString);
        Token token = tokenGenerator.validateToken("aVcPZagEGxTyswuM-MNdMlFTfFydJCzh5yQX4LmozpLGxLwfveUlxQpSoVGajCaX_NYVkpOX5iJrYDamHI3x_PchO2K9LKMs-J2XKpxYTOn_zDbjk3iw96GbaMDUQA5o");
        System.out.println(JSONObject.toJSONString(token));
    }
}
