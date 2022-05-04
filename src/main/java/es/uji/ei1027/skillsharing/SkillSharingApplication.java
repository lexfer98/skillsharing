package es.uji.ei1027.skillsharing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class SkillSharingApplication implements CommandLineRunner{
	private static final Logger log = Logger.getLogger(SkillSharingApplication .class.getName());

	public static void main(String[] args) {
		// Auto-configura l'aplicació
		new SpringApplicationBuilder(SkillSharingApplication.class).run(args);
	}
	public void run(String... strings) throws Exception {
		log.info("Ací va el meu codi");
	}
}
