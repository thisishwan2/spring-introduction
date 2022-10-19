package hello.springintroduction.controller;

public class MemberForm {
    private String name;    //setName을 통해 name이 설정됨,

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
