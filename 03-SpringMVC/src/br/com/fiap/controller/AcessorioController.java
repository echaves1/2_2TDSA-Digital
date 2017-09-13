package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.dao.AcessorioDAO;
import br.com.fiap.model.Acessorio;

@Controller
@RequestMapping("/acessorio")
public class AcessorioController {

	private AcessorioDAO dao;
	
	//Abre tela do Formul�rio
	@GetMapping("/cadastrar")
	public ModelAndView abrirForm(){
		return new ModelAndView("acessorio/cadastro").addObject("acessorio", new Acessorio());
	}
	
	//Processa as informa��es do Formul�rio
	@Transactional
	@PostMapping("cadastrar")
	public ModelAndView processaForm(Acessorio acessorio, RedirectAttributes redirect){
		dao.cadastrar(acessorio);
		ModelAndView retorno = new ModelAndView("redirect:/acessorio/listar");
		redirect.addFlashAttribute("msg", "Acess�rio cadastrado!");
		return retorno;
		
	}
	
	
}