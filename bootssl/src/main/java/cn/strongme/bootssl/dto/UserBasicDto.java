package cn.strongme.bootssl.dto;

import cn.strongme.bootssl.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-03 17:39.
 */


@Data
@NoArgsConstructor
public class UserBasicDto extends BaseEntity<UserEsDto> {

    private static final long serialVersionUID = -116975554378377250L;

    private String dutyId;

    private String name;

    private String username;

    private String password;

    private String status;

    private String sign;

}
