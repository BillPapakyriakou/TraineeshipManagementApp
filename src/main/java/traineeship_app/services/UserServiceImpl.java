package traineeship_app.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.User;
import traineeship_app.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userDAO;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper userDAO) {
        this.bCryptPasswordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    @Override
    public void saveUser(User user){

    }

    @Override
    public boolean isUserPresent(User user){
        return false;
    }

    @Override
    public User findById(String username){
        return null;
    }

    @Override
    public long getUserIdByUsername(String username){
        return userDAO.findUserIdByUsername(username);
    }


/*
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
    }*/
}
