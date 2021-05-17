package com.imooc.oa.dao;

import com.imooc.oa.entity.Node;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    @Select("SELECT DISTINCT " +
            "n.* " +
            "FROM " +
            "sys_role_user ru, sys_role_node rn, sys_node n " +
            "where ru.role_id = rn.role_id and ru.user_id = #{userId} and n.node_id = rn.node_id " +
            "ORDER BY node_code")
    List<Node> selectNodeByUserId(Long userId);


}
