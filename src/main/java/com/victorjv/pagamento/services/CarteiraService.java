package com.victorjv.pagamento.services;


import com.victorjv.pagamento.entities.Carteira;
import com.victorjv.pagamento.entities.dtos.CarteiraDTO;
import com.victorjv.pagamento.entities.enums.TIPO;
import com.victorjv.pagamento.repositories.CarteiraRepository;
import com.victorjv.pagamento.services.exceptions.TransacaoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository repository;

    public void debitar(Double valor, Carteira carteira){
        carteira.debitar(valor);
        repository.save(carteira);
    }

    public void creditar(Double valor, Carteira carteira){
        carteira.creditar(valor);
        repository.save(carteira);
    }


    public Carteira insert(Carteira carteira){

        return repository.save(carteira);
    }

    public List<CarteiraDTO> findAll(){

        List<CarteiraDTO> list2 = repository.findAll().stream().map(CarteiraService::toDTO).toList();

        return list2;
    }

    public CarteiraDTO findById(Long id){

        return CarteiraService.toDTO(repository.findById(id).orElseThrow(() -> new TransacaoError("Carteira não encontrada")));
    }

    public CarteiraDTO finbyCpf_cnpj(String documento){


        Optional<Carteira> carteira = repository.findByDocumento(documento);

        if (carteira.stream().toList().isEmpty()) {
            throw new RuntimeException("Nenhuma carteira encontrada com o documento " + documento);
        }

        return CarteiraService.toDTO(carteira.stream().toList().getFirst());
    }

    public void deleteById(Long id){
        findById(id);
        repository.deleteById(id);
    }

    public Carteira update(Carteira obj){

        Carteira carteira = repository.getReferenceById(obj.getId());
        updateDate(carteira, obj);

        return repository.save(carteira);
    }

    private void updateDate(Carteira carteira, Carteira obj){


        carteira.setNome_completo(obj.getNome_completo());
        carteira.setDocumento(obj.getDocumento());
        carteira.setEmail(obj.getEmail());
        carteira.setSenha(obj.getSenha());
        carteira.setTipo(obj.getTipo());
    }

    public static CarteiraDTO toDTO(Carteira carteira){
        return new CarteiraDTO(carteira.getId(),carteira.getNome_completo(),carteira.getDocumento(),
                carteira.getEmail(),carteira.getSaldo(),TIPO.getTipo(carteira.getTipo()));
    }

    public Carteira toCarteira(CarteiraDTO carteiraDTO){
        return repository.findById(carteiraDTO.id()).orElse(null);
    }

}
