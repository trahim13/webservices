package org.trahim.rest.webservices2.helloword_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trahim.rest.webservices2.helloword_jpa.entity.User;

public interface UserJPARepository extends JpaRepository<User, Integer> {
}
