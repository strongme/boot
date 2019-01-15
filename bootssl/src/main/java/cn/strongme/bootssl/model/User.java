package cn.strongme.bootssl.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 系统用户
 *
 * @author Walter
 */
@NoArgsConstructor
@Data
@TableName(value = "sys_user")
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = -9147411076559979926L;

    private String orgId;

    private String dutyId;

    private String name;

    private String username;

    private String password;

    @TableField(exist = false)
    private String newPassword;

    private String email;

    private String gender;

    private String mobile;

    private String avatar;

    private String status;

    private String sign;

    public User(String id) {
        this.id = id;
    }

}
