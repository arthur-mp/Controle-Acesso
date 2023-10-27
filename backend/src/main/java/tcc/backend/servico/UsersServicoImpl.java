package tcc.backend.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.backend.dto.LoginDTO;
import tcc.backend.dto.UsagesDTO;
import tcc.backend.dto.UsersDTO;
import tcc.backend.entidade.Usages;
import tcc.backend.entidade.Users;
import tcc.backend.repositorio.IUsersRepositorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServicoImpl implements IUsersServico{

    @Autowired
    private IUsersRepositorio usersRepositorio;

    @Autowired
    private UsagesServicoImpl usagesServico;


    @Override
    public UsersDTO create(UsersDTO userDTO) {
        return createOrEdit(userDTO);
    }
    @Override
    public UsersDTO edit(UsersDTO userDTO) {
        return createOrEdit(userDTO);
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

    @Override
    public UsersDTO buscaPorCodigoTag(String codigoTag){
        Optional<Users> user = usersRepositorio.findByCodigoTag(codigoTag);

        if (user.isPresent()) {

            UsagesDTO usagesDTO = usagesServico.buscarPorCodigoTag(codigoTag);

            if(usagesDTO != null && usagesDTO.getDateUsageOutput() == null){
                // Caso de saida
                // Preencher dateUsageInput e timeUsageInput com a data e hora atuais
                LocalDateTime currentDateTime = LocalDateTime.now();

                // Formatar a data e hora no formato desejado
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                String currentDate = currentDateTime.format(dateFormatter);
                String currentTime = currentDateTime.format(timeFormatter);

                usagesDTO.setDateUsageOutput(currentDate);
                usagesDTO.setTimeUsageOutput(currentTime);

                UsagesDTO usages = usagesServico.edit(usagesDTO);
            }else{
                if((usagesDTO != null && usagesDTO.getDateUsageOutput() != null) || usagesDTO == null) {
                    // Caso de nova entrada
                    UsagesDTO newUsage = new UsagesDTO();
                    newUsage.setId("");
                    newUsage.setIdUser(user.get().getId());
                    newUsage.setTagUser(codigoTag);

                    // Preencher dateUsageInput e timeUsageInput com a data e hora atuais
                    LocalDateTime currentDateTime = LocalDateTime.now();

                    // Formatar a data e hora no formato desejado
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                    String currentDate = currentDateTime.format(dateFormatter);
                    String currentTime = currentDateTime.format(timeFormatter);

                    newUsage.setDateUsageInput(currentDate);
                    newUsage.setTimeUsageInput(currentTime);

                    newUsage.setDateUsageOutput(null);
                    newUsage.setTimeUsageOutput(null);

                    UsagesDTO usages = usagesServico.create(newUsage);

                    List<String> usagesId = user.get().getUsagesId();

                    if(usagesId == null){
                        usagesId = new ArrayList<>();
                    }
                    usagesId.add(usages.getId());
                    user.get().setUsagesId(usagesId);
                    edit(this.userToUserDTO(user.get()));
                }
            }

            return userToUserDTO(user.get());
        } else {
            return null;
        }

    }

    private UsersDTO createOrEdit(UsersDTO usersDTO){
        Users novoUser = userDTOToUser(usersDTO);

        Users user = usersRepositorio.save(novoUser);

        UsersDTO userDTOCriado = userToUserDTO(user);

        return userDTOCriado;
    }

    private UsersDTO userToUserDTO(Users user){
        UsersDTO userDTO = new UsersDTO();
        if(!user.getId().isEmpty()) userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setSobrenome(user.getSobrenome());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setMatricula(user.getMatricula());
        userDTO.setEmail(user.getEmail());
        userDTO.setCodigoTag(user.getCodigoTag());
        userDTO.setSenha(user.getSenha());
        userDTO.setVeiculosId(user.getVeiculosId());

        return userDTO;
    };

    private Users userDTOToUser(UsersDTO userDTO){
        Users user = new Users();
        if(!userDTO.getId().isEmpty()) user.setId(userDTO.getId());
        user.setNome(userDTO.getNome());
        user.setSobrenome(userDTO.getSobrenome());
        user.setTelefone(userDTO.getTelefone());
        user.setMatricula(userDTO.getMatricula());
        user.setEmail(userDTO.getEmail());
        user.setCodigoTag(userDTO.getCodigoTag());
        user.setSenha(userDTO.getSenha());
        user.setVeiculosId(userDTO.getVeiculosId());

        return user;
    }
}
