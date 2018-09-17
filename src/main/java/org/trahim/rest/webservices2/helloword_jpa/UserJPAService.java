package org.trahim.rest.webservices2.helloword_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.trahim.rest.webservices2.helloword_jpa.entity.Post;
import org.trahim.rest.webservices2.helloword_jpa.entity.User;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAService {
    @Autowired
    private UserJPARepository repository;

    @Autowired
    private PostJPARepository postJPARepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{userId}")
    public Resource<User> retrieveUser(@PathVariable int userId) {
        Optional<User> userOptional = repository.findById(userId);
        if (!userOptional.isPresent()) {
            //если пользоватеь не найден то возврат статуса not found 404 и id из запроса
            throw new UserNotFoundException("id - " + userId);
        }
//добавление ссылок в ответ
        Resource<User> resource = new Resource<>(userOptional.get());
        //ссылка 1
        ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        //ссылка 1
        ControllerLinkBuilder link2 = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveUser(userOptional.get().getId()));
        //названия ссылок
        resource.add(link.withRel("all-users"));
        //названия ссылок
        resource.add(link2.withRel("get-user"));
        return resource;
    }

    @PostMapping("jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        //чтобы возращадась ссылка на созданный объек фомата /users/{userId} и статут CREATED 201
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("jpa/users/{userId}")
    public void deleteById(@PathVariable int userId) {

        repository.deleteById(userId);

    }
//@JsonIgnore по полю user в классе Post
    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> retrieveAllUsersPosts(@PathVariable int userId) {
        Optional<User> userOptional = repository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("Can not found user with id=" + userId);
        }

        return userOptional.get().getPosts();
    }

//сохранение поста пользователя
    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity<Object> saveUserPost(@PathVariable int userId, @RequestBody Post post) {
        Optional<User> userOptional = repository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("Can not found user with id=" + userId);
        }

        post.setUser(userOptional.get());

        Post savedPost = postJPARepository.save(post);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(post.getId())
                .toUri();


        return ResponseEntity.created(uri).build();

    }
}
