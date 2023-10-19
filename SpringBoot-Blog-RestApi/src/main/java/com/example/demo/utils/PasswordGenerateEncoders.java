package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerateEncoders {
	public static void main(String[] args) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("deep"));
//		System.out.println(encoder.encode("admin"));

		
	}

}
