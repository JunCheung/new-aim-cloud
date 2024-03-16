package com.newaim.module.system.convert.user;

import com.newaim.framework.common.util.collection.CollectionUtils;
import com.newaim.framework.common.util.object.BeanUtils;
import com.newaim.module.system.controller.admin.user.vo.user.UserRespVO;
import com.newaim.module.system.controller.admin.user.vo.user.UserSimpleRespVO;
import com.newaim.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    default List<UserRespVO> convertList(List<AdminUserDO> list) {
        return CollectionUtils.convertList(list, this::convert);
    }

    default UserRespVO convert(AdminUserDO user) {
        return BeanUtils.toBean(user, UserRespVO.class);
    }

    default List<UserSimpleRespVO> convertSimpleList(List<AdminUserDO> list) {
        return CollectionUtils.convertList(list, user -> BeanUtils.toBean(user, UserSimpleRespVO.class));
    }
}
