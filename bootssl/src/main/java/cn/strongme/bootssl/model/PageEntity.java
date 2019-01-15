package cn.strongme.bootssl.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 阿水
 * @date 2018/5/5 14:43
 */
@Data
@NoArgsConstructor
public class PageEntity<T> implements Serializable {

    private static final long serialVersionUID = -8854874359098665932L;

    protected T t;

    protected Long current = 0L;

    protected Long size = 10L;

    public Page<T> getPage() {
        Page<T> page = new Page<>(current, size);
        return page;
    }


}
