package com.shopme.admin.usuario;

import com.shopme.common.entity.Papel_Perfil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Papel_PerfilRepository extends CrudRepository<Papel_Perfil, Integer> {

}
