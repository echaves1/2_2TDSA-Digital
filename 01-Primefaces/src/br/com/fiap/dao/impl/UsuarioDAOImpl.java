package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO{

	public UsuarioDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Usuario> buscarPorNome(String nome) {
		return em.createQuery("from Usuario u where upper(u.nome) "
				+ "like upper(:n)",
			Usuario.class).setParameter("n", "%"+nome+"%").getResultList();
	}

	@Override
	public List<String> completarPorNome(String texto) {
		return em.createQuery("select u.nome from Usuario u where"
				+ " upper(u.nome) "
				+ "like upper(:n)",String.class)
				.setParameter("n", "%" + texto + "%")
				.getResultList();
	}

	@Override
	public long buscarPorNivel(int nivel) {
		return em.createQuery("select count(u) from Usuario u "
				+ "where u.nivel = :n",Long.class)
				.setParameter("n", nivel)
				.getSingleResult();
	}

	@Override
	public long contarPorMesAniversario(int mes) {
		return em.createQuery("select count(u) from Usuario u"
				+ " where month(u.dataNascimento) = :m",Long.class)
				.setParameter("m", mes)
				.getSingleResult();
	}

}
