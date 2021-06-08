package com.summit.dao.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author: jeerper
 * @date: 2021-05-28 14:53:33
 */
public class User {
    private String id;
    private String name;
    private Double money;
    private Double money11;
    private List<String> hobby;

    public User() {
    }

    public Double getMoney11() {
        return money11;
    }

    public void setMoney11(Double money11) {
        this.money11 = money11;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String id, String name, List<String> hobby) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
    }

    public User(String id,String name, Double money, Double money11) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.money11 = money11;
    }

    public User(String id, String name, Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
