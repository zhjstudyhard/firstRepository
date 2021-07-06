package cn.xydata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-09-03
 */

@MappedSuperclass
public class BaseEntity {

   @Column(name = "gmtcreate")
   private Date gmtcreate;

   @Column(name = "gmtmodified")
   private Date gmtmodified;

   /**
    * 用户是否被删除
    */
   @Column(name = "isdeleted")
   private Integer isDeleted;

   /**
    * 用户创建时间
    */
   @Column(name = "namecreated")
   private String namecreated;

   /**
    * 用户修改时间
    */
   @Column(name = "namemodified")
   private String namemodified;

   /**
    * 用户过期
    */
   @Column(name = "expired")
   private Date expired;

   /**
    * 用户锁定
    */
   @Column(name = "locked")
   private Integer locked;


   public Date getGmtcreate() {
      return gmtcreate;
   }

   public void setGmtcreate(Date gmtcreate) {
      this.gmtcreate = gmtcreate;
   }

   public Date getGmtmodified() {
      return gmtmodified;
   }

   public void setGmtmodified(Date gmtmodified) {
      this.gmtmodified = gmtmodified;
   }

   public Integer getIsDeleted() {
      return isDeleted;
   }

   public void setIsDeleted(Integer isDeleted) {
      this.isDeleted = isDeleted;
   }

   public String getNamecreated() {
      return namecreated;
   }

   public void setNamecreated(String namecreated) {
      this.namecreated = namecreated;
   }

   public String getNamemodified() {
      return namemodified;
   }

   public void setNamemodified(String namemodified) {
      this.namemodified = namemodified;
   }

   public Date getExpired() {
      return expired;
   }

   public void setExpired(Date expired) {
      this.expired = expired;
   }

   public Integer getLocked() {
      return locked;
   }

   public void setLocked(Integer locked) {
      this.locked = locked;
   }
}
