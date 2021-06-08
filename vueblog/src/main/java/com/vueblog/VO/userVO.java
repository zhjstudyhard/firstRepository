package com.vueblog.VO;

import com.vueblog.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-08-13-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userVO {
    private Integer userId;
    private String userName;
    private List<Role> Roles;
}
