package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import traineeship_app.domainmodel.User;

//  UserMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

public interface UserMapper extends JpaRepository<User, Long> {

    // Custom query method to find user by username
    User findByUsername(String username);

}