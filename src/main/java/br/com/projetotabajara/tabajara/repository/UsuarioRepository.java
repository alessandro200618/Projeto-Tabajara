package br.com.projetotabajara.tabajara.repository;

import br.com.projetotabajara.tabajara.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
     Optional<Usuario> findByLoginUsuario(String loginUsuario);
}