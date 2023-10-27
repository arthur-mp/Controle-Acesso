package tcc.backend.servico;

import tcc.backend.dto.LoginDTO;
import tcc.backend.dto.UsersDTO;

import java.util.List;

public interface IUsersServico {

    UsersDTO create(UsersDTO usersDTO);

    UsersDTO edit(UsersDTO usersDTO);

    UsersDTO getOne(String idUser);

    List<UsersDTO> getAll();

    UsersDTO delete(String idUser);

    UsersDTO login(LoginDTO loginDTO);

    UsersDTO buscaPorCodigoTag(String codigoTag);
}
