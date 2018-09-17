package org.trahim.rest.webservices2.helloword_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore//иначе будет ошибка,припопытке получения из user всех егопостов, так как получиться цикл вызовов Юзер получает посты, посты получают Юзера
    private User user;


}
