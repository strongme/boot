package cn.strongme.bootssl.webp;

import io.github.biezhi.webp.WebpIO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-05 08:54.
 */

@Slf4j
public class WebpConvertTest {


    @Test
    public void testToJpg() throws IOException {

        File src = ResourceUtils.getFile("classpath:img/1678246178911mb.jpg.webp");
        String parentDir = src.getParent();
        log.info("parent: {}", parentDir);
        File target = new File(parentDir + File.separator + "1678246178911mb.jpg");
        if (!target.exists()) {
            target.createNewFile();
        }
        WebpIO.create().toNormalImage(src, target);

    }

    @Test
    public void testToWebp() throws IOException {
        File src = ResourceUtils.getFile("classpath:img/1678246178911mb.jpg");
        String parentDir = src.getParent();
        log.info("parent: {}", parentDir);
        File target = new File(parentDir + File.separator + "1678246178911mb.jpg.webp");
        if (!target.exists()) {
            target.createNewFile();
        }
        WebpIO.create().toWEBP(src, target);
    }

}
