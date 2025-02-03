package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.EpreuveRequest;
import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.response.EpreuveResponse;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.services.ExerciceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Exercice ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExerciceApi {

    private final ExerciceService exerciceService;

    @PostMapping("/exercice")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Exercice", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ExerciceResponse> saveExercice(@RequestBody ExerciceRequest epreuveRequest) {
        return ResponseEntity.ok(exerciceService.save(epreuveRequest));
    }

    @PutMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ExerciceResponse> updateExercice(@PathVariable UUID trackingId, @RequestBody ExerciceRequest request) {
        return ResponseEntity.ok(exerciceService.update(trackingId, request));
    }

    @DeleteMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteExerciceByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.exerciceService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Exercice  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ExerciceResponse> getOneExercice(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(exerciceService.getOne(trackingId));
    }

    @GetMapping("/epreuve/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Exercice  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ExerciceResponse>> update() {
        return ResponseEntity.ok(exerciceService.listAll());
    }

    @GetMapping("/epreuve/all/{trackingId}/{matiereID}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ExerciceResponse>> listAllByNiveauAndMatiere(@PathVariable UUID trackingId, @PathVariable UUID matiereID) {
        return ResponseEntity.ok(exerciceService.listAllByNiveauAndMatiere(trackingId,matiereID));
    }


}
