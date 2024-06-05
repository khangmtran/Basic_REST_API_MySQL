package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.MyUser;
import com.example.demo.repository.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private MyUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<MyUser> myUser = repo.findByUserName(username);
		
		if (myUser.isPresent()) {
			return User.builder().username(myUser.get().getUserName())
					.password(myUser.get().getPassWord())
					.roles(myUser.get().getRole())
					.build();
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
	
	public MyUser saveUser(MyUser user) {
		return repo.save(user);
	}

}
