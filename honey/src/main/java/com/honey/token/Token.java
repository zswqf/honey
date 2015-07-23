package com.honey.token;

public class Token {
	private String id;
	private String key;
	private long expirationTime;

	public Token(String id, long expirationTime,String key) {
		super();
		this.id = id;
		this.key = key;
		this.expirationTime = expirationTime;
	}

	public Token(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Token)
			return ((Token) obj).id.equals(this.id);
		return false;
	}

}
