package traineeship_app.services;

import traineeship_app.domainmodel.User;

public interface UserService {

    void saveUser(User user);  // saves or updates a user

    boolean isUserPresent(User user);  // checks if the user is present

    User findById(String username);  // finds user by username

    long getUserIdByUsername(String username);
}
