package com.shopme.admin.usuario;

import com.shopme.common.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
   @Query("SELECT u FROM Usuario  u WHERE  u.email =:email")
   public Usuario findByEmail(@Param("email") String email);

   public Long countById(Integer id);
}