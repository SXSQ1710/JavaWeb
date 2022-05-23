package cn.com.huyi.JODO;

/**
 * @title: User
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/17 20:19
 **/

public class User {
    private int id;
    private String name;
    private int password;

    public void add(){
        System.out.println("add......");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public User() {
    }

    public User(int id, String name, int password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}
