package org.apache.camel.kafkaconnector;

import java.io.Serializable;

public class Author implements Serializable {

   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Author [name=" + name + "]";
   }
}
