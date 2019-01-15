package cn.strongme.bootssl;

import cn.strongme.bootssl.dto.UserBasicDto;
import cn.strongme.bootssl.dto.UserEsDto;
import cn.strongme.bootssl.repository.UserRepository;
import cn.strongme.bootssl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-03 12:50.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootsslApplication.class)
@Slf4j
public class UserServiceTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(UserEsDto.class);
        elasticsearchTemplate.createIndex(UserEsDto.class);
        elasticsearchTemplate.putMapping(UserEsDto.class);
        elasticsearchTemplate.refresh(UserEsDto.class);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void initUserDataToEs() {
        List<UserEsDto> userList = userService.findEsDtoList();
        for (UserEsDto user : userList) {
            userRepository.save(user);
        }
    }

    @Test
    public void testMybatis() {
        List<UserBasicDto> userBasicDtoList = userService.findBasicDtoList();
        assertThat(userBasicDtoList).isNotNull();
        assertThat(userBasicDtoList.size()).isGreaterThan(0);
    }

}
