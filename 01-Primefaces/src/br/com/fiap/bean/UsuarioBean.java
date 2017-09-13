package br.com.fiap.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.entity.Usuario;
import br.com.fiap.exception.DBException;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	
	private UsuarioBO bo;
	
	public StreamedContent getFoto(){
		DefaultStreamedContent content = new DefaultStreamedContent();
		//Configurar o tipo de arquivo
		content.setContentType("image/jpg");
		
		try{
			if (FacesContext.getCurrentInstance().getRenderResponse() ||
					usuario.getFoto() == null){
				content.setStream(new FileInputStream("C:\\temp\\fotopadrao.jpg"));
			}else{
				//content.setStream(new FileInputStream("C:\\temp\\" + usuario.getFoto()));
				content.setStream(new ByteArrayInputStream(usuario.getFoto()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
	
	//Método que realiza o upload de arquivo
	public void subirFoto(FileUploadEvent event){
		//Cadastra a foto no banco
		usuario.setFoto(event.getFile().getContents());
		/* Cadastra no disco
		 try {
			usuario.setFoto(event.getFile().getFileName());
			//Cria um arquivo com o nome do arquivo do upload
			File file = new File("C:\\temp\\", event.getFile().getFileName());
			//Gravar informação no arquivo
			FileOutputStream output = new FileOutputStream(file);
			output.write(event.getFile().getContents());
			output.close();
			//Grava o nome do arquivo no banco
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	@PostConstruct
	private void init(){
		//Inicializar os objetos
		usuario = new Usuario();
		usuario.setDataNascimento(Calendar.getInstance());
		bo = new UsuarioBO();
	}
	
	public String cadastrar(){
		FacesMessage msg;
		try {
			if (usuario.getCodigo() == 0){
				bo.cadastrar(usuario);
				msg = new FacesMessage("Cadastrado!");
			}else{
				bo.atualizar(usuario);
				msg = new FacesMessage("Atualizado!");
			}
			usuario = new Usuario();
			usuario.setDataNascimento(Calendar.getInstance());
		} catch (DBException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro...");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext()
									.getFlash().setKeepMessages(true);
		return "usuario?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
