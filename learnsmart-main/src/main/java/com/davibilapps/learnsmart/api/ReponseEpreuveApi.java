package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.ReponseEpreuveRequest;
import com.davibilapps.learnsmart.dto.request.ReponseRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.ReponseEpreuveResponse;
import com.davibilapps.learnsmart.dto.response.ReponseResponse;
import com.davibilapps.learnsmart.services.ReponseEpreuveService;
import com.davibilapps.learnsmart.services.ReponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Reponse Epreuve ", description = "Endpoint of reponse Epreuve")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReponseEpreuveApi {

    private final ReponseEpreuveService reponseService;

    @PostMapping("/reponseEpreuve")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new reponse Epreuve", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ReponseEpreuveResponse> saveReponse(@RequestBody ReponseEpreuveRequest epreuveRequest) {
        return ResponseEntity.ok(reponseService.save(epreuveRequest));
    }

    @PutMapping("/reponseEpreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a reponse Epreuve by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ReponseEpreuveResponse> updateReponse(@PathVariable UUID trackingId, @RequestBody ReponseEpreuveRequest request) {
        return ResponseEntity.ok(reponseService.update(trackingId, request));
    }

    @DeleteMapping("/reponseEpreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  reponse Epreuve by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteReponseByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.reponseService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/reponseEpreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "reponse Epreuveby TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ReponseEpreuveResponse> getOneReponse(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(reponseService.getOne(trackingId));
    }

    @GetMapping("/reponseEpreuve/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste reponse Epreuve  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ReponseEpreuveResponse>> update() {
        return ResponseEntity.ok(reponseService.listAll());
    }

    @GetMapping("/reponseEpreuve/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   reponse Epreuve by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ReponseEpreuveResponse>> listAllByEpreuve(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(reponseService.listAllByEpreuve(trackingId));
    }
}
