package com.ticketexchange.auth;

import java.time.Instant;

public class MemberToken {
	private Long id;
	private Long expire;

	protected MemberToken() {
	}

	public MemberToken(Long id, Long expire) {
		this.id = id;
		this.expire = expire;
	}

	public Long getId() {
		return id;
	}

	public Long getExpire() {
		return expire;
	}

	public boolean isExpired() {
		return Instant.now().toEpochMilli() > expire;
	}
}
