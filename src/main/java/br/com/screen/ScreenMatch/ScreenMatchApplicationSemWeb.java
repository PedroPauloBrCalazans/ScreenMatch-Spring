//package br.com.screen.ScreenMatch;
//
//import br.com.screen.ScreenMatch.principal.Principal;
//import br.com.screen.ScreenMatch.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenMatchApplicationSemWeb implements CommandLineRunner {
//
//	@Autowired
//	private SerieRepository serieRepository; //injeção de dependencia + classe que o spring gerencia
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenMatchApplicationSemWeb.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(serieRepository);
//		principal.exibeMenu();
//	}
//}
