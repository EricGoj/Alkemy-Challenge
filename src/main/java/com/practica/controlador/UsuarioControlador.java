package com.practica.controlador;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.practica.entidades.Usuario;
import com.practica.repositorio.UsuarioDAO;
import com.practica.servicio.EmailService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UsuarioControlador {

	@Autowired
	private UsuarioDAO usuarioservice;

	@Autowired
	private EmailService emailService;

	@PostMapping("/auth/login")
	public Usuario login2(@RequestBody Usuario usu) {
		List<Usuario> usuarios = usuarioservice.findAll();
		for (Usuario usuar : usuarios)
			if (usu.getUsuario().equals(usuar.getUsuario()) && usu.getPassword().equals(usuar.getPassword())) {
				String token = getJWTToken(usu.getUsuario());
				usuar.setToken(token);
				return usuar;
			}
		return null;
	}

	@PostMapping(path = "/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public String register(@RequestBody Usuario usu) throws IOException {
		usuarioservice.save(usu);
		emailService.sendTextEmail(usu.getEmail(), usu.getUsuario());
		return "Realizado";
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				// Token valido x 1 hora
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
