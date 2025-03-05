package traineeship_app.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import traineeship_app.domainmodel.User;
import traineeship_app.mappers.UserMapper;

public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;  // Used for password encryption
                                                         // Automatically injects the PasswordEncoder bean

    @Autowired
    private UserMapper userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);  // Get user from our database
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        // Check if user already exists
        if (userDAO.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("User already exists with username: " + user.getUsername());
        }

        // Encrypt the password before saving the user
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDAO.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        User storedUser = userDAO.findByUsername(user.getUsername());
        return storedUser != null;
    }

    @Override
    public User findById(String username) {
        return userDAO.findByUsername(username);
    }
}
