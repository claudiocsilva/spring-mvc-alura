package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	private ContaDAO contaDao;

	@Autowired
	public ContaController(ContaDAO contaDao) {
		this.contaDao = contaDao;
	}

	/**
	 * Redireciona para a página de cadastrar uma conta.
	 * 
	 * @return formulario.jsp
	 */
	@RequestMapping("/form")
	public String formulario() {

		return "formulario";
	}

	/**
	 * Redireciona para a página de lista
	 * 
	 * @return listaContas.jsp
	 */
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {

		if (result.hasErrors()) {
			return "formulario";
		}

		System.out.println("Adicionado uma conta....\n Conta adicionada é: " + conta.getDescricao());

		contaDao.adiciona(conta);
		return "redirect:listaContas";
	}

	/**
	 * Método responsável para remover uma conta, em seguida redireciona para a
	 * listaContas
	 * 
	 * @return listaContas.jsp
	 */
	@RequestMapping("removeConta")
	public String remove(Conta conta) {
		contaDao.remove(conta);
		return "redirect:listaContas";
	}

	@RequestMapping("pagaConta")
	public void paga(Conta conta, HttpServletResponse response) {
		contaDao.paga(conta.getId());

		response.setStatus(200);
	}

	/**
	 * Redireciona para a página de lista
	 * 
	 * @return listaContas.jsp
	 */
	@RequestMapping("/listaContas")
	public String lista(Model mv) {
		
		List<Conta> contas = contaDao.lista();

		mv.addAttribute("todasContas", contas);
		return "conta/lista";
	}

	/**
	 * Redireciona para a página de lista
	 * 
	 * @return listaContas.jsp
	 */
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model model) {
		model.addAttribute("conta", contaDao.buscaPorId(id));
		return "conta/mostra";
	}

	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
		contaDao.altera(conta);
		return "redirect:listaContas";
	}

}
