package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.ReponseRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.ReponseResponse;
import com.davibilapps.learnsmart.services.ReponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Reponse ", description = "Endpoint of Reponse")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReponseApi {

    private final ReponseService reponseService;

    @PostMapping("/reponse")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new reponse", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ReponseResponse> saveReponse(@RequestBody ReponseRequest epreuveRequest) {
        return ResponseEntity.ok(reponseService.save(epreuveRequest));
    }

    @PutMapping("/reponse/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a reponse by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ReponseResponse> updateReponse(@PathVariable UUID trackingId, @RequestBody ReponseRequest request) {
        return ResponseEntity.ok(reponseService.update(trackingId, request));
    }

    @DeleteMapping("/reponse/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteReponseByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.reponseService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/reponse/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Exercice  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<ReponseResponse> getOneReponse(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(reponseService.getOne(trackingId));
    }

    @GetMapping("/reponse/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "reponse by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ReponseResponse>> update() {
        return ResponseEntity.ok(reponseService.listAll());
    }

    @GetMapping("/reponse/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   reponse by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<ReponseResponse>> allReponseByQuestion(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(reponseService.listAllByQuestion(trackingId));
    }

}
