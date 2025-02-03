package com.davibilapps.learnsmart.api;


import com.davibilapps.learnsmart.dto.request.AnneeScolaireRequest;
import com.davibilapps.learnsmart.dto.response.AnneeScolaireResponse;
import com.davibilapps.learnsmart.services.AnneeScolaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "AnneeScolaire ", description = "Endpoint of anneeScolaire")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AnneeScolaireApi {

    private final AnneeScolaireService anneeScolaireService;

    @PostMapping("/annee-scolaire")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "Add new AnneeScolaire",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / InvaltrackingId Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<AnneeScolaireResponse> saveAnneeScolaire(@RequestBody AnneeScolaireRequest anneeScolaireRequest) {
        return ResponseEntity.ok(anneeScolaireService.save(anneeScolaireRequest));
    }

    @GetMapping("/annee-scolaire")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "List of AnneeScolaire",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / InvaltrackingId Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<List<AnneeScolaireResponse>> getAllAnneeScolaire() {
        return ResponseEntity.ok(anneeScolaireService.listAll());
    }

    @PutMapping("/annee-scolaire/{trackingId}")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "Update a AnneeScolaire by TrackingId",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / InvaltrackingId Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<AnneeScolaireResponse> updateAnneeScolaire(@PathVariable UUID trackingId, @RequestBody AnneeScolaireRequest anneeScolaireRequest){
        return ResponseEntity.ok(anneeScolaireService.update(trackingId,anneeScolaireRequest));
    }

    @DeleteMapping("/annee-scolaire/{trackingId}")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "Delete  AnneeScolaire by TrackingId",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<Void> deleteAnneeScolaireByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.anneeScolaireService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/annee-scolaire/enabled/{trackingId}")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "Enabled a AnneeScolaire by TrackingId",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<Void> enabledAnneeScolaireByTrackingId(@PathVariable("trackingId") UUID trackingId) {

        this.anneeScolaireService.enable(trackingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/annee-scolaire-true")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "Annee actif of AnneeScolaire",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / InvaltrackingId Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<AnneeScolaireResponse> getAnneeScolaireTrue() {
        return ResponseEntity.ok(anneeScolaireService.getAnneeTrue());
    }

    @GetMapping("/annee-scolaire/get-one/{trackingId}")
    @Operation(
            description = "This endpoint require a valid JWT",
            summary = "Get one AnneeScolaire",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / InvaltrackingId Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<AnneeScolaireResponse> getAnneeScolaire(@PathVariable("trackingId") UUID trackingId) {
        return ResponseEntity.ok(anneeScolaireService.getOne(trackingId));
    }


}
