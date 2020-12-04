package com.masterdevskills.hello.repo;

import java.time.LocalDateTime;

public class User {
	private final String id;
	private final String name;
	private final String email;
	private final boolean verified;
	private final String twitterHandle;
	private final LocalDateTime createdAt;
	private final LocalDateTime lastModified;

	public User(String id, String name, String email, boolean verified, String twitterHandle, LocalDateTime createdAt, LocalDateTime lastModified) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.verified = verified;
		this.twitterHandle = twitterHandle;
		this.createdAt = createdAt;
		this.lastModified = lastModified;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public boolean isVerified() {
		return verified;
	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}
}
