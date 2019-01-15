package cn.strongme.bootssl.service;

import cn.strongme.bootssl.dto.UserBasicDto;
import cn.strongme.bootssl.dto.UserEsDto;
import cn.strongme.bootssl.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-03 12:49.
 */


public interface UserService extends IService<User> {

    List<UserEsDto> findEsDtoList();

    List<UserBasicDto> findBasicDtoList();

}
