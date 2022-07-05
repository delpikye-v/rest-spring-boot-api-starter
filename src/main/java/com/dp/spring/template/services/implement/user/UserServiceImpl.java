package com.dp.spring.template.services.implement.user;

import com.dp.spring.template.services.interfaces.user.UserService;
import com.dp.spring.template.exceptions.DuplicateException;
import com.dp.spring.template.exceptions.NotFoundException;
import com.dp.spring.template.models.user.User;
import com.dp.spring.template.repositories.address.AddressRepository;
import com.dp.spring.template.repositories.user.UserRepository;
import com.dp.spring.template.services.implement.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    /*@Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AddressRepository addressRepository;*/

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public User create(User user) {
        /*Address address = addressRepository.save(user.getAddress());
        user.setAddress(address);*/
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found User with id = " + id));
    }

    public void validateUserByName(String username) {
        if (userRepository.existsByUsername(username))
            new DuplicateException("Error: Username is already taken!");
    }

    public void validateUserByEmail(String email) {
        if (userRepository.existsByEmail(email))
            new DuplicateException("Error: Email is already taken!");
    }
}
