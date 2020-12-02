package com.revature.utilities;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

//Minimalistic, all work done in the constructor, only a get method for security.
public class Hasher {
	
	private static Logger log = Logger.getLogger(Hasher.class);
	
	private String hashed;
	
	public Hasher() {}

	public Hasher(String password) {
		super();
		this.hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		if(BCrypt.checkpw(password, this.hashed)) {
			log.info("Password hashed successfully.");
		}else {
			log.warn("WARNING: Password not hashed.");
		}	
	}

	public String getHashed() {
		return hashed;
	}

}
