package org.trahim.rest.webservices2.helloword_jpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@ApiModel(description = "All detais about the user")
//description будет добавлено в api документацию как опесание всего объекта USER

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, message = "Name should be at least 2 characters")
    @ApiModelProperty(notes = "Name should be at least 2 characters") //notes будет добавлено в api документацию как опесание данного поля
    private String name;

    @Past
    @ApiModelProperty(notes = "Can't be in futer") //будет добавлено в api документацию как опесание данного поля
    private Date bithDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
