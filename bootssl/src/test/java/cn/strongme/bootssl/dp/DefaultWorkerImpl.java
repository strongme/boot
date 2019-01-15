package cn.strongme.bootssl.dp;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-04 09:16.
 */


public class DefaultWorkerImpl implements OriginWorker {
    @Override
    public String getMyName(String id) {
        return id + "-" + "I am Id";
    }
}
