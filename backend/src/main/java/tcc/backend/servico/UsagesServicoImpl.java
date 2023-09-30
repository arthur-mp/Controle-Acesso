package tcc.backend.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.backend.dto.UsagesDTO;
import tcc.backend.entidade.Usages;
import tcc.backend.repositorio.IUsagesRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsagesServicoImpl implements IUsagesServico{

    @Autowired
    private IUsagesRepositorio usagesRepositorio;


    @Override
    public UsagesDTO create(UsagesDTO usagesDTO) {
        return createOrEdit(usagesDTO);
    }
    @Override
    public UsagesDTO edit(UsagesDTO usagesDTO) {
        return createOrEdit(usagesDTO);
    }
    @Override
    public UsagesDTO getOne(String idUsages) {
        Optional<Usages> UsagesOptional = usagesRepositorio.findById(idUsages);

        if (UsagesOptional.isPresent()){
            Usages Usages = UsagesOptional.get();
            return UsageToUsageDTO(Usages);
        }
        return null;
    }

    @Override
    public List<UsagesDTO> getAll() {
        List<UsagesDTO> UsagesDTO = new ArrayList<>();

        List<Usages> Usages = usagesRepositorio.findAll();

        if(!Usages.isEmpty()){
            Usages.forEach(usage -> {
                UsagesDTO.add(UsageToUsageDTO(usage));
            });
        }

        return UsagesDTO;
    }
    @Override
    public UsagesDTO delete(String idUsages) {
        Optional<Usages> usagesOptional = usagesRepositorio.findById(idUsages);

        if (usagesOptional.isPresent()){
            Usages usage = usagesOptional.get();
            usagesRepositorio.delete(usage);

            return UsageToUsageDTO(usage);
        }
        return null;
    }
    private UsagesDTO createOrEdit(UsagesDTO UsagesDTO){
        Usages novoUsage = UsageDTOToUsage(UsagesDTO);

        Usages usage = usagesRepositorio.save(novoUsage);

        UsagesDTO usageDTOCriado = UsageToUsageDTO(usage);

        return usageDTOCriado;
    }

    private UsagesDTO UsageToUsageDTO(Usages usage){
        UsagesDTO usagesDTO = new UsagesDTO();
        if(!usage.getId().isEmpty()) usagesDTO.setId(usage.getId());
        usagesDTO.setDateUsage(usage.getDateUsage());
        usagesDTO.setTimeUsage(usage.getTimeUsage());
        usagesDTO.setIdUser(usage.getIdUser());
        usagesDTO.setTagUser(usage.getTagUser());

        return usagesDTO;
    };

    private Usages UsageDTOToUsage(UsagesDTO usageDTO){
        Usages usage = new Usages();
        if(!usageDTO.getId().isEmpty()) usage.setId(usageDTO.getId());
        usage.setDateUsage(usageDTO.getDateUsage());
        usage.setTimeUsage(usageDTO.getTimeUsage());
        usage.setIdUser(usageDTO.getIdUser());
        usage.setTagUser(usageDTO.getTagUser());

        return usage;
    }
}
