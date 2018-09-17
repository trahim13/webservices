package org.trahim.rest.webservices2.heloword2.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.trahim.rest.webservices2.heloword2.dao.UserDaoService;
import org.trahim.rest.webservices2.heloword2.user.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserService {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{userId}")
    public Resource<User> retrieveUser(@PathVariable int userId) {
        User user = service.findOne(userId);
        if (user == null) {
            //если пользоватеь не найден то возврат статуса not found 404 и id из запроса
            throw new UserNotFoundException("id - " + userId);
        }
//добавление ссылок в ответ
        Resource<User> resource = new Resource<>(user);
        //ссылка 1
        ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        //ссылка 1
        ControllerLinkBuilder link2 = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveUser(user.getId()));
        //названия ссылок
        resource.add(link.withRel("all-users"));
        //названия ссылок
        resource.add(link2.withRel("get-user"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        //чтобы возращадась ссылка на созданный объек фомата /users/{userId} и статут CREATED 201
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedUser.getId())
                .toUri();

         return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteById(@PathVariable int userId) {

        User user = service.delete(userId);

        if (user == null) {
            throw new UserNotFoundException("can not fouduser with id -" + userId);
        }
    }


}
