package com.sz.bikememoback.controllers;

import com.sz.bikememoback.dtos.CreateNotationMessage;
import com.sz.bikememoback.dtos.NotationMessage;
import com.sz.bikememoback.models.Notation;
import com.sz.bikememoback.services.NotationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/v1/notation")
public class NotationRestAdapter {
    private final NotationService notationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    //@SecurityRequirement(name = "bearer-jwt")
    //@SecurityRequirement(name = "oauth2")
    @Operation(
            tags = "notation",
            summary = "Alle Notitzen",
            description = "Gibt eine Liste aller Notitzen",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste der Notations",
                            content =
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotationMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Nicht authentifiziert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "403", description = "Nicht authorisiert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "400", description = "Fehlerhafte Anfrage",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    public List<NotationMessage> getAllNotation() {
        //todo - return alle notitze per BenutzerId, id aus jwt
        return notationService.getAllNotations().stream().map(NotationMessage::of).toList();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    //@SecurityRequirement(name = "bearer-jwt")
    //@SecurityRequirement(name = "oauth2")
    @Operation(
            tags = "notation",
            summary = "Eine Notiz",
            description = "Gibt eine Notiz per Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Eine Notiz per Id",
                            content =
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotationMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Nicht authentifiziert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "403", description = "Nicht authorisiert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "400", description = "Fehlerhafte Anfrage",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    public NotationMessage getNotation(@RequestParam Long id) {
        return NotationMessage.of(notationService.getNotationById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    //@SecurityRequirement(name = "bearer-jwt")
    //@SecurityRequirement(name = "oauth2")
    @Operation(
            tags = "notation",
            summary = "Aktualisiert eine Notiz",
            description = "Aktualisiert eine Notiz per Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Eine Notiz aktualisiert wurde",
                            content =
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotationMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Nicht authentifiziert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "403", description = "Nicht authorisiert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "400", description = "Fehlerhafte Anfrage",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    public NotationMessage updateNotation(@RequestBody NotationMessage notationMessage) {
        return NotationMessage.of(notationService.updateNotation(Notation.of(notationMessage)));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    //@SecurityRequirement(name = "bearer-jwt")
    //@SecurityRequirement(name = "oauth2")
    @Operation(
            tags = "notation",
            summary = "Erstellt eine Notiz",
            description = "Erstellt eine Notiz per gegebene NotationMessage",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Eine Notiz aktualisiert wurde",
                            content =
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotationMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Nicht authentifiziert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "403", description = "Nicht authorisiert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "400", description = "Fehlerhafte Anfrage",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    public NotationMessage createNotation(@RequestBody CreateNotationMessage notationMessage) {
        return NotationMessage.of(notationService.createNotation(Notation.create(notationMessage)));
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    //@SecurityRequirement(name = "bearer-jwt")
    //@SecurityRequirement(name = "oauth2")
    @Operation(
            tags = "notation",
            summary = "Löscht eine Notiz",
            description = "Löscht eine Notiz per gegebene Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Eine Notiz gelöscht wurde"),
                    @ApiResponse(responseCode = "401", description = "Nicht authentifiziert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "403", description = "Nicht authorisiert",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "400", description = "Fehlerhafte Anfrage",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    public void deleteNotation(@RequestParam Long id) {
        notationService.deleteNotation(id);
    }
}
