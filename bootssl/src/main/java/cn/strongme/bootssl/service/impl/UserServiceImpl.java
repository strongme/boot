package cn.strongme.bootssl.service.impl;

import cn.strongme.bootssl.dao.UserDao;
import cn.strongme.bootssl.dto.UserBasicDto;
import cn.strongme.bootssl.dto.UserEsDto;
import cn.strongme.bootssl.model.User;
import cn.strongme.bootssl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-03 12:49.
 */

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public List<UserEsDto> findEsDtoList() {
        return baseMapper.findEsDtoList();
    }

    @Override
    public List<UserBasicDto> findBasicDtoList() {
        return baseMapper.findBasicDtoList();
    }
}
