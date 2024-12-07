package com.alberto.workoutapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alberto.workoutapp.dto.UserDTO;
import com.alberto.workoutapp.dto.UserMinDTO;
import com.alberto.workoutapp.dto.UserNewDTO;
import com.alberto.workoutapp.entities.Role;
import com.alberto.workoutapp.entities.User;
import com.alberto.workoutapp.projections.UserDetailsProjection;
import com.alberto.workoutapp.repositories.RoleRepository;
import com.alberto.workoutapp.repositories.UserRepository;
import com.alberto.workoutapp.services.exceptions.BadRequestException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
		if (result.size() == 0) {
			throw new UsernameNotFoundException("Email not found");
		}

		User user = new User();
		user.setEmail(result.get(0).getUsername());
		user.setPassword(result.get(0).getPassword());
		for (UserDetailsProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}

		return user;
	}

	protected User authenticated() { // só pode ser chamado nas classes service
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return repository.findByEmail(username).get();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
	}

	@Transactional
	public UserMinDTO insert(UserNewDTO userNewDto) {

		if (repository.findByEmail(userNewDto.getEmail()).isPresent()) {
			throw new BadRequestException("Email already exists");
		}

		if (!userNewDto.getPassword().equals(userNewDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }
		User entity = new User();
		entity.setName(userNewDto.getName());
		entity.setEmail(userNewDto.getEmail());
		entity.setPassword(passwordEncoder.encode(userNewDto.getPassword()));
		Role role = roleRepository.findById(1L).get();
		entity.addRole(role);
		entity = repository.save(entity);
		return new UserMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public UserDTO getMe() {
		User user = authenticated();
		return new UserDTO(user);
	}

}
