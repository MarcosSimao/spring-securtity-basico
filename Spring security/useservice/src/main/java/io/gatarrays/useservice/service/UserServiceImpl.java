package io.gatarrays.useservice.service;

import io.gatarrays.useservice.entites.Role;
import io.gatarrays.useservice.entites.User;
import io.gatarrays.useservice.repository.RoleRepository;
import io.gatarrays.useservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User saveUser(User user) {
        log.info("salvando novos usuarios {} no banco de dados",user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("salvando novos cargos {} no banco de dados",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
      log.info("adicionando cargos {} no usuario {}",roleName,username);
        User user= userRepository.findByUserName(username);
      Role role= roleRepository.findByName(roleName);
      user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("buscando usuario por username {}",username);
        User user=userRepository.findByUserName(username);
        return user;
    }

    @Override
    public List<User> getUsers() {
        log.info("listando todos os usuarios");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user==null){
            log.error("Usuario nao existe no banco de dados");
            throw new UsernameNotFoundException("Usuario nao existe no banco de dados");
        }else{
            log.info("usuario encontrado {}"+username);
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorities);
    }
}
