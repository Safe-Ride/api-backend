package saferide.sptech.apibackend.service.autentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.repository.ClienteRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Cliente> usuarioOpt = clienteRepository.findByEmail(username);
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuário '%s' não encontrado", username));
        }
        return new ClienteDetalhesDto(usuarioOpt.get());
    }
}