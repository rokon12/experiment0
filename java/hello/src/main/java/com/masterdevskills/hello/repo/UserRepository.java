package com.masterdevskills.hello.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserRepository {
	public List<User> findAllUsers() {

		return List.of(
						new User(UUID.randomUUID().toString(), "Bazlur Rahman", "bazlur@xyz.org", true, "@bazlur_rahman", LocalDateTime.now(), LocalDateTime.now()),
						new User(UUID.randomUUID().toString(), "James Gosling", "james@xyz.org", true, "@errcraft", LocalDateTime.now(), LocalDateTime.now()),
						new User(UUID.randomUUID().toString(), "Barack Obama", "barack@xyz.org", true, "@BarackObama", LocalDateTime.now(), LocalDateTime.now()));
	}
}
