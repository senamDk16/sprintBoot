package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.MatiereRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.MatiereResponse;
import com.davibilapps.learnsmart.services.MatiereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Matiere ", description = "Endpoint of matiere")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MatiereApi {

    private final MatiereService matiereService;

    @PostMapping("/matiere")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new matiere", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<MatiereResponse> saveEnseignant(@RequestBody MatiereRequest epreuveRequest) {
        return ResponseEntity.ok(matiereService.save(epreuveRequest));
    }

    @PutMapping("/matiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<MatiereResponse> updateEnseignant(@PathVariable UUID trackingId, @RequestBody MatiereRequest request) {
        return ResponseEntity.ok(matiereService.update(trackingId, request));
    }

    @DeleteMapping("/matiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteEnseignantByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.matiereService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/matiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "matiere   by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<MatiereResponse> getOneEnseignant(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(matiereService.getOne(trackingId));
    }

    @GetMapping("/matiere/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste matiere  by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<MatiereResponse>> update() {
        return ResponseEntity.ok(matiereService.listAll());
    }

    @GetMapping("/matiere/allByType/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<MatiereResponse>> listAllByType(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(matiereService.listAllByType(trackingId));
    }

    @GetMapping("/matiere/allBycategorie/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   matiere by TrackingId categorie", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<MatiereResponse>> listAllByCategorie(@PathVariable UUID catrgorie) {
        return ResponseEntity.ok(matiereService.listAllByCategorie(catrgorie));
    }


}
