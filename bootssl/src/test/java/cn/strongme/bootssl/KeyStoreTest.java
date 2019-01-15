package cn.strongme.bootssl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-28 11:31.
 */


@Slf4j
public class KeyStoreTest {

    @Test
    public void test() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        log.info("Default KeyStore Type: {}",KeyStore.getDefaultType());
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        char[] pwdArr = "123456".toCharArray();
        keyStore.load(null, pwdArr);



        for (Enumeration iter = keyStore.aliases(); iter.hasMoreElements();) {
            String alias = (String) iter.nextElement();
            Certificate cert = keyStore.getCertificate(alias);
            log.info("Cert: {}, PublicKey:{}", cert.getType(), cert.getPublicKey());
        }


    }

}
