package cn.strongme.bootssl.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-12-13 16:37.
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T extends Model> extends Model<T> {

    private static final long serialVersionUID = -1747598982089441725L;

    protected String id;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
