package br.com.projetotabajara.tabajara.repository;

import br.com.projetotabajara.tabajara.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}