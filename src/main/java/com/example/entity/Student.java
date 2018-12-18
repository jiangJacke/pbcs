package com.example.entity;

import javax.persistence.*;

/**
 * @Author: jiangm
 * @Description:
 * @CreateDate: 2018/8/28 16:19
 * @Version: PBCS 1.0
 */
@Entity
@Table(name="Student")
public class Student {
    @Id   //标注用于声明一个实体类的属性映射为数据库的主键列
    @GeneratedValue(strategy = GenerationType.AUTO) //用于标注主键的生成策略，通过strategy 属性指定
    /*–IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
    –AUTO： JPA自动选择合适的策略，是默认选项；
    –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
    –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植*/
    private String name;
    @Column
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
