package org.trahim.rest.webservices2.helloword_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trahim.rest.webservices2.helloword_jpa.entity.Post;

public interface PostJPARepository extends JpaRepository<Post, Integer> {
}
