package br.com.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.dao.VeiculoDAO;
import br.com.fiap.model.Veiculo;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {
	
	@Autowired //Servidor vai injetar um objeto aqui
	private VeiculoDAO dao;

	@Transactional
	@PostMapping("/excluir")
	public ModelAndView excluirForm(int codigo, RedirectAttributes redirect){
		
		try {
			dao.remover(codigo);
			redirect.addFlashAttribute("msg", "Excluido!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/veiculo/listar");
	}
	
	@Transactional
	@GetMapping("editar/{id}")
	public ModelAndView abrirFormEdicao(@PathVariable("id") int codigo){
		Veiculo veiculo = dao.pesquisar(codigo);
		return new ModelAndView("veiculo/edicao").addObject("veiculo", veiculo);
	}
	
	@PostMapping
	public ModelAndView processaFormEdicaoVeiculo(Veiculo veiculo, RedirectAttributes redirect){
		dao.alterar(veiculo);
		redirect.addFlashAttribute("msg", "Atualizado!");
		return new ModelAndView("redirect:/veiculo/listar");
	}
	
	
	@GetMapping("cadastrar") //Abre a tela de formul�rio
	public ModelAndView abrirForm(){
		return new ModelAndView("veiculo/cadastro")
							.addObject("veiculo", new Veiculo());
	}
	
	@Transactional
	@PostMapping("cadastrar") //Processa as informa��es do formul�rio
	public ModelAndView processaForm(Veiculo veiculo, RedirectAttributes redirect){
		//Cadastrar no banco
		dao.cadastrar(veiculo);
		//Retorno.. redirecionar para o m�todo listar
		ModelAndView retorno = new ModelAndView("redirect:/veiculo/listar");
		redirect.addFlashAttribute("msg", "Cadastrado com sucesso!");
		return retorno;
	}
	
	
	@GetMapping("listar")
	public ModelAndView listarVeiculos(){
		ModelAndView retorno = new ModelAndView("veiculo/lista");
		retorno.addObject("veiculos",dao.listar());
		return retorno;
	}
	
}




