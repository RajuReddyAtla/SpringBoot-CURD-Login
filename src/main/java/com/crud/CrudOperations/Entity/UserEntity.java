package com.crud.CrudOperations.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String UserName;
    private  int Age;
    private String Address;
    private String email;
    private String password;



        public long getUserEntityid(){
    return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName(){
            return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public int getAge(){
            return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
    public String getAddress(){
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getEmail(){
            return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }
    public String getPassword(){
            return password;
    }
    public void setPassword(String Password){
            this.password=Password;
    }

    public UserEntity(long id,String UserName,int Age,String Address,String Email,String Password)
    {
        super();
        this.id=id;
        this.UserName=UserName;
        this.Age=Age;
        this.Address=Address;
        this.email=Email;
        this.password=Password;

    }
    public UserEntity(){
            super();
    }
    @Override
    public String toString(){
            return "UserEntity[id="+id+",UserName="+UserName+",Age="+Age+",Address="+Address+",Email="+email+",Password="+password+"]";
    }

}
