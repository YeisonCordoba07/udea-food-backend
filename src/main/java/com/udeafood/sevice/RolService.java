package com.udeafood.sevice;

import com.udeafood.model.Rol;
import com.udeafood.repository.IRolRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RolService {

    private final IRolRepository iRolRepository;




    public List<Rol> getAll(){
        return iRolRepository.findAll();
    }
}
