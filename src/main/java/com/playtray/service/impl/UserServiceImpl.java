package com.playtray.service.impl;

import com.playtray.model.entity.Product;
import com.playtray.service.exception.ObjectNotFoundException;
import com.playtray.model.dto.UserDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.playtray.constants.ExceptionMessages.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            User user = mapToUser(userRegisterDTO);

            userRepository.save(user);
        }
    }

    private User mapToUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user
                .setUsername(userRegisterDTO.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .setEmail(userRegisterDTO.getEmail())
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setActive(false)
                .setRoles(List.of(roleRepository.findByName(UserRole.USER)))
                .setCart(new Cart().setCustomer(user));

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException(USER_ID_NOT_FOUND + id));

        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public void addUserRole(String username, Long roleId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(USER_USERNAME_NOT_FOUND + username));

        Role roleToAdd = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ObjectNotFoundException(
                                ROLE_ID_NOT_FOUND + roleId));

        for (Role role : user.getRoles()) {
            if (role.getId().equals(roleId)) {
                throw new IllegalArgumentException(
                        "Role " + role.getName().name() + " already assigned to user " + username);
            }
        }
        user.getRoles().add(roleToAdd);

        userRepository.save(user);
    }

    @Override
    public void removeUserRole(String username, Long roleId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(USER_USERNAME_NOT_FOUND + username));

        user.getRoles().removeIf(userRole -> userRole.getId().equals(roleId));

        userRepository.save(user);
    }

    @Override
    public Map<String, BigDecimal> getTotalSales() {
        Map<String, BigDecimal> sales = new HashMap<>();
        sales.put("Units", BigDecimal.ZERO);
        sales.put("Amount", BigDecimal.ZERO);

        List<User> users = userRepository.findAllByBoughtProductsIsNotEmpty();

        users
                .stream()
                .map(User::getBoughtProducts)
                .forEach(products -> {
                    BigDecimal currentUnits = BigDecimal.valueOf(products.size());
                    BigDecimal oldUnits = sales.get("Units");
                    BigDecimal newUnits = oldUnits.add(currentUnits);

                    sales.put("Units", newUnits);

                    BigDecimal currentAmount =
                            products
                                    .stream()
                                    .map(Product::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                    BigDecimal oldAmount = sales.get("Amount");
                    BigDecimal newAmount = oldAmount.add(currentAmount);

                    sales.put("Amount", newAmount);
                });

        return sales;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(USER_USERNAME_NOT_FOUND + username));
    }

    @Override
    public void saveUser(User customer) {
        userRepository.save(customer);
    }

    @Override
    public boolean isUniqueUsername(String value) {
        return userRepository.findByUsername(value).isEmpty();
    }

    @Override
    public boolean isUniqueEmail(String value) {
        return userRepository.findByEmail(value).isEmpty();
    }
}
