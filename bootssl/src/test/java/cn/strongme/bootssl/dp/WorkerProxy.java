package cn.strongme.bootssl.dp;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-04 09:17.
 */


public class WorkerProxy implements OriginWorker {

    private OriginWorker proxied;

    public WorkerProxy(OriginWorker proxied) {
        this.proxied = proxied;
    }

    @Override
    public String getMyName(String id) {
        return proxied.getMyName(id)+" {proxied}";
    }
}
