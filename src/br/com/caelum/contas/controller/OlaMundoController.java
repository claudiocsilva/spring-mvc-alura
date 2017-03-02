package br.com.caelum.contas.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	
	@RequestMapping("/olaMundoSpring")
	public String telaOk() throws SQLException {
		System.out.println("Avançando de tela");
		return "olaMundoSpring";

	}

}
