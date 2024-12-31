package example.Crud.controller;

import example.Crud.model.Usuario;
import example.Crud.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Usuario", description = "API crud de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Obtener todos los usuarios",
                description = "Retorna una lista de todos los usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios encontrada")

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Crear un nuevo usuario",
            description = "Crea un nuevo usuario con la información proporcionada")
    @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente")

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Actualizar un usuario",
            description = "Actualiza un usuario existente con la información proporcionada")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")

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
