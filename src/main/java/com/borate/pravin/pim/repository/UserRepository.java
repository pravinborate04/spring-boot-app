package com.borate.pravin.pim.repository;

import com.borate.pravin.pim.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Pravin Borate
 * 12/03/21
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, QueryByExampleExecutor<User> {

    @Query("select user from User user where user.email = :email")
    public User findUserByEmail(String email);


}
