package com.oose.jobportal.services.impls;

import com.oose.jobportal.models.entities.User;
import com.oose.jobportal.repositories.UserRepo;
import com.oose.jobportal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean saveUser(User user) {
		User userSave = new User();
		userSave = userRepo.save(user);
		if(userSave != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findUserbyGmail(String gmail) {
		User userGmail = new User();
		userGmail = userRepo.findByGmail(gmail);
		if(userGmail != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkAccount(String gmail, String pass) {
		try {
			User user = userRepo.findByGmail(gmail);

			if (passwordEncoder.matches(pass, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
