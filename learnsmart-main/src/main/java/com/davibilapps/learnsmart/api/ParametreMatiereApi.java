package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.ParametrageMatiereRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.ParametrageMatiereResponse;
import com.davibilapps.learnsmart.services.ParametreMatiereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Parametre Matiere ", description = "Endpoint of Parametre Matiere")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParametreMatiereApi {

    private final ParametreMatiereService parametreMatiereService;

    //il reste un truc a terminé looo
    @PostMapping("/parametreMatiere")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Parametre Matiere", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ParametrageMatiereResponse> saveParametreMatiere(@RequestBody ParametrageMatiereRequest epreuveRequest) {
        return ResponseEntity.ok(parametreMatiereService.save(epreuveRequest));
    }


    //il reste un truc a terminé looo

    @PutMapping("/parametreMatiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ParametrageMatiereResponse> updateParametreMatiere(@PathVariable UUID trackingId, @RequestBody ParametrageMatiereRequest request) {
        return ResponseEntity.ok(parametreMatiereService.update(trackingId, request));
    }

    @DeleteMapping("/parametreMatiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Parametre Matiereby TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteParametreMatiereByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.parametreMatiereService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/parametreMatiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Parametre Matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ParametrageMatiereResponse> getOneParametreMatiere(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(parametreMatiereService.getOne(trackingId));
    }

    @GetMapping("/parametreMatiere/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Parametre Matiere by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ParametrageMatiereResponse>> update() {
        return ResponseEntity.ok(parametreMatiereService.listAll());
    }

    @GetMapping("/parametreMatiere/all/{trackingId}}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   Parametre Matiere  by classe TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ParametrageMatiereResponse>> listAllByClasse(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(parametreMatiereService.listAllByClasse(trackingId));
    }


}
