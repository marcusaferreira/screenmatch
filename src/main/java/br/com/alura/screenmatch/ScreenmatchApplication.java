package br.com.alura.screenmatch;

import br.com.alura.screenmatch.infra.jpa.repository.SerieORMRepository;
import br.com.alura.screenmatch.principal.Principal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public ScreenmatchApplication(@Value("${omdb.api.key}") String omdbApiKey, SerieORMRepository serieORMRepository) {
		this.omdbApiKey = omdbApiKey;
		this.serieORMRepository = serieORMRepository;
	}

	static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	private final String omdbApiKey;
	private final SerieORMRepository serieORMRepository;

	@Override
	public void run(String... args) {
		Principal principal = new Principal(omdbApiKey, serieORMRepository);
		principal.exibeMenu();
	}
}
