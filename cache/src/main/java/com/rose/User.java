package com.rose;

import java.io.Serializable;

public class User implements Serializable {

        private String username;
        private Integer age;

        public User(String username, Integer age) {
            this.username = username;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }
    }