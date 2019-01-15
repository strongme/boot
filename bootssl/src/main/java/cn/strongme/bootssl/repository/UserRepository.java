package cn.strongme.bootssl.repository;

import cn.strongme.bootssl.dto.UserEsDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-02 20:57.
 */


public interface UserRepository extends ElasticsearchRepository<UserEsDto, String> {

}
