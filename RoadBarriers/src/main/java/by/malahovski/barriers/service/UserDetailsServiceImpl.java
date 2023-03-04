package by.malahovski.barriers.service;

import by.malahovski.barriers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.malahovski.barriers.models.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}

	public Boolean deleteUserById(Long id) throws UsernameNotFoundException {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Boolean readLog(String fileName) {
		try {
			String prices = null;
			File src = new File(fileName);
			FileReader reader = new FileReader(src);
			Scanner scanner = new Scanner(reader);
			while (scanner.hasNextLine()) {
				prices = scanner.nextLine();
			}
			System.out.println("Log read: " + fileName);
			System.out.println(prices);
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}
}
