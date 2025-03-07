package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.User;


//  UserMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

@Repository
public interface UserMapper extends JpaRepository<User, Long> {
    // id (Long) acts as the primary key here

    // Custom query method to find user by username
    User findByUsername(String username);

}