package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.NiveauRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.NiveauResponse;
import com.davibilapps.learnsmart.services.NiveauService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Niveau ", description = "Endpoint of Niveau")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NiveauApi {

    private final NiveauService niveauService;

    @PostMapping("/exercice")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Niveau", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<NiveauResponse> saveNiveau(@RequestBody NiveauRequest epreuveRequest) {
        return ResponseEntity.ok(niveauService.save(epreuveRequest));
    }

    @PutMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Niveau by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<NiveauResponse> updateNiveau(@PathVariable UUID trackingId, @RequestBody NiveauRequest request) {
        return ResponseEntity.ok(niveauService.update(trackingId, request));
    }

    @DeleteMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Niveau by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteNiveauByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.niveauService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Niveau  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<NiveauResponse> getOneNiveau(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(niveauService.getOne(trackingId));
    }

    @GetMapping("/epreuve/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Niveau  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<NiveauResponse>> update() {
        return ResponseEntity.ok(niveauService.listAll());
    }

    @GetMapping("/epreuve/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   Niveau by serie TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<NiveauResponse>> listAllBySerie(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(niveauService.listAllBySerie(trackingId));
    }

}
