package hello.springintroduction.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 entity가 된다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //pk는 id이고, db가 알아서 만들어 주는건 Identity라고 한다.
    private Long id;


    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
