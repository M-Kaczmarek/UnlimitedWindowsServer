package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.model.AuthenticationRequest;
import pl.unlimited.windows.serwer.model.AuthenticationResponse;
import pl.unlimited.windows.serwer.model.SystemUser;
import pl.unlimited.windows.serwer.model.dto.TransportDocumentDto;
import pl.unlimited.windows.serwer.security.service.MyUserDetailsService;
import pl.unlimited.windows.serwer.security.util.JwtUtil;

@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Autentykacjia", description = "Endpointy zarządzającec autentykacja użytkownika")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    @Operation(
            summary = "Logowanie użytkownika",
            description = "Zaloguj użytkownika",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = AuthenticationResponse.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    @Operation(
            summary = "Rejestracja użytkownika",
            description = "Zarejestruj użytkownika",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = SystemUser.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<SystemUser> registerCandidate(@RequestBody SystemUser systemUser){
        return ResponseEntity.ok(userDetailsService.saveCandidate(systemUser));
    }
}
