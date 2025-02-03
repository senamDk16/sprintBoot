package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.SerieRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.SerieResponse;
import com.davibilapps.learnsmart.entity.Serie;
import com.davibilapps.learnsmart.services.SerieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Serie ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SerieApi {

    private final SerieService serieService;

    @PostMapping("/serie")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Exercice", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<SerieResponse> saveSerie(@RequestBody SerieRequest serieRequest) {
        return ResponseEntity.ok(serieService.save(serieRequest));
    }

    @PutMapping("/serie/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<SerieResponse> updateSerie(@PathVariable UUID trackingId, @RequestBody SerieRequest request) {
        return ResponseEntity.ok(serieService.update(trackingId, request));
    }

    @DeleteMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteSerieByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.serieService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Exercice  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<SerieResponse> getOneSerie(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(serieService.getOne(trackingId));
    }

    @GetMapping("/epreuve/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Exercice  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<SerieResponse>> update() {
        return ResponseEntity.ok(serieService.listAll());
    }

    @GetMapping("/epreuve/all/{trackingId}/{matiereID}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<SerieResponse>> updateSerie(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(serieService.listAllByCycle(trackingId));
    }
}
