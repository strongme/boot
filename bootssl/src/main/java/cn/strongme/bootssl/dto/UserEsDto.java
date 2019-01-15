package cn.strongme.bootssl.dto;

import cn.strongme.bootssl.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-03 15:36.
 */

@Data
@NoArgsConstructor
@Document(indexName = "bootssl", type = "user")
public class UserEsDto extends BaseEntity<UserEsDto> {

    private static final long serialVersionUID = -3519938611132257174L;

    private String name;

    private String username;

}
