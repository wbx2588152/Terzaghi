package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Adve  implements Serializable {

      private String id;

      private String name;

      private String img;

      private String href;

      private Integer price;

      @DateTimeFormat(pattern="yyyy-MM-dd")
      @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
      private Date expiredate;

      private String place;

      private String showdate;

      public String getId() {
            return id;
      }

      public void setId(String id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getImg() {
            return img;
      }

      public void setImg(String img) {
            this.img = img;
      }

      public String getHref() {
            return href;
      }

      public void setHref(String href) {
            this.href = href;
      }

      public Integer getPrice() {
            return price;
      }

      public void setPrice(Integer price) {
            this.price = price;
      }

      public Date getExpiredate() {
            return expiredate;
      }

      public void setExpiredate(Date expiredate) {
            this.expiredate = expiredate;
      }

      public String getPlace() {
            return place;
      }

      public void setPlace(String place) {
            this.place = place;
      }

      public String getShowdate() {
            return showdate;
      }

      public void setShowdate(String showdate) {
            this.showdate = showdate;
      }
}
