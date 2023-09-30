package tcc.backend.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.backend.dto.LoginDTO;
import tcc.backend.dto.UsersDTO;
import tcc.backend.entidade.Users;
import tcc.backend.repositorio.IUsersRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServicoImpl implements IUsersServico{

    @Autowired
    private IUsersRepositorio usersRepositorio;


    @Override
    public UsersDTO create(UsersDTO veiculoDTO) {
        return createOrEdit(veiculoDTO);
    }
    @Override
    public UsersDTO edit(UsersDTO veiculoDTO) {
        return createOrEdit(veiculoDTO);
    }
    @Override
    public UsersDTO getOne(String idUser) {
        Optional<Users> userOptional = usersRepositorio.findById(idUser);

        if (userOptional.isPresent()){
            Users user = userOptional.get();
            return userToUserDTO(user);
        }
        return null;
    }

    @Override
    public List<UsersDTO> getAll() {
        List<UsersDTO> usersDTOS = new ArrayList<>();

        List<Users> users = usersRepositorio.findAll();

        if(!users.isEmpty()){
            users.forEach(user -> {
                usersDTOS.add(userToUserDTO(user));
            });
        }

        return usersDTOS;
    }
    @Override
    public UsersDTO delete(String idUser) {
        Optional<Users> usersOptional = usersRepositorio.findById(idUser);

        if (usersOptional.isPresent()){
            Users user = usersOptional.get();
            usersRepositorio.delete(user);

            return userToUserDTO(user);
        }
        return null;
    }

    @Override
    public UsersDTO login(LoginDTO loginDTO) {

        Optional<Users> users = usersRepositorio.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());

        if (users.isPresent()) {
            return userToUserDTO(users.get());
        } else {
            return null;
        }
    }

    private UsersDTO createOrEdit(UsersDTO usersDTO){
        Users novoUser = userDTOToUser(usersDTO);

        Users veiculo = usersRepositorio.save(novoUser);

        UsersDTO userDTOCriado = userToUserDTO(veiculo);

        return userDTOCriado;
    }

    private UsersDTO userToUserDTO(Users user){
        UsersDTO userDTO = new UsersDTO();
        if(!user.getId().isEmpty()) userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setSobrenome(user.getSobrenome());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setMatricula(user.getMatricula());
        userDTO.setMatricula(user.getMatricula());
        userDTO.setEmail(user.getEmail());
        userDTO.setCodigoTag(user.getCodigoTag());
        userDTO.setSenha(user.getSenha());
        userDTO.setVeiculosId(user.getVeiculosId());
        userDTO.setUsagesId(user.getUsagesId());

        return userDTO;
    };

    private Users userDTOToUser(UsersDTO userDTO){
        Users user = new Users();
        if(!userDTO.getId().isEmpty()) user.setId(userDTO.getId());
        user.setNome(userDTO.getNome());
        user.setSobrenome(userDTO.getSobrenome());
        user.setTelefone(userDTO.getTelefone());
        user.setMatricula(userDTO.getMatricula());
        user.setMatricula(userDTO.getMatricula());
        user.setEmail(userDTO.getEmail());
        user.setCodigoTag(userDTO.getCodigoTag());
        user.setSenha(userDTO.getSenha());
        user.setVeiculosId(userDTO.getVeiculosId());
        user.setUsagesId(userDTO.getUsagesId());

        return user;
    }
}
