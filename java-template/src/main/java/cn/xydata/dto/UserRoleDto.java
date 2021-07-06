package cn.xydata.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-02-15-18
 */
public class UserRoleDto {

   /**
    * 用户id
    */
   @NotNull(message = "userId不能为空")
   private String userId;

   /**
    * 角色ids
    */
   private List<String> roleIds;

   public UserRoleDto(@NotNull(message = "userId不能为空") String userId, List<String> roleIds) {
      this.userId = userId;
      this.roleIds = roleIds;
   }

   public UserRoleDto() {
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public List<String> getRoleIds() {
      return roleIds;
   }

   public void setRoleIds(List<String> roleIds) {
      this.roleIds = roleIds;
   }

   @Override
   public String toString() {
      return "UserRoleDto{" +
              "userId='" + userId + '\'' +
              ", roleIds=" + roleIds +
              '}';
   }
}
