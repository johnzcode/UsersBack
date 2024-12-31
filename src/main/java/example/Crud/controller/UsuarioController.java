package example.Crud.controller;

import example.Crud.model.Usuario;
import example.Crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario existeUsuario = usuarioRepository.findById(id).orElse(null);
        if (existeUsuario != null){
            existeUsuario.setNombre(usuario.getNombre());
            existeUsuario.setEmail(usuario.getEmail());
            existeUsuario.setTelefono(usuario.getTelefono());

            Usuario actualizarUser = usuarioRepository.save(existeUsuario);
            return ResponseEntity.ok(actualizarUser);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user == null){
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
