package cn.strongme.bootssl.dao;

import cn.strongme.bootssl.dto.UserBasicDto;
import cn.strongme.bootssl.dto.UserEsDto;
import cn.strongme.bootssl.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-03 12:48.
 */

@Repository
public interface UserDao extends BaseMapper<User> {

    @ResultMap("esDto")
    @Select("select id, username, name from sys_user")
    List<UserEsDto> findEsDtoList();

    @Select("select * from sys_user")
    List<UserBasicDto> findBasicDtoList();

}
