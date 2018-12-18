package com.example.entity;

import javax.persistence.*;

/**
 * @Author: jiangm
 * @Description:  test1表测试查询
 * @CreateDate: 2018/8/18 15:45
 * @Version: PBCS 1.0
 */
@Entity
@Table(name="test1")
public class VisVehicle {
    @Id
    @GeneratedValue
    private Integer Id_P;
    @Column
    //private String LastName;
    //private String FirstName;  first_name
    private String Address;
    private String City;

    public Integer getId_P() {
        return Id_P;
    }

    public void setId_P(Integer id_P) {
        Id_P = id_P;
    }

  //  public String getFirstName() {
  //      return FirstName;
  //  }

  //  public void setFirstName(String firstName) {
  //      FirstName = firstName;
   // }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public VisVehicle(){

    }

    @Override
    public String toString() {
        return "VisVehicle{" +
                "Id_P=" + Id_P +
           //     ", LastName='" + LastName + '\'' +
           //     ", FirstName='" + FirstName + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}