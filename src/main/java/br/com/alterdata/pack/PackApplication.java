package br.com.alterdata.pack;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alterdata.pack.service.UsuarioServiceImpl;

@SpringBootApplication
public class PackApplication {

	public static void main(String[] args) {
		
		new File(UsuarioServiceImpl.uploadDirectory).mkdir();
		
		SpringApplication.run(PackApplication.class, args);
	}

}
