package com.example.reactivespring.api;

import com.example.reactivespring.domain.Profile;
import com.example.reactivespring.service.ProfileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final ProfileService profileService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    Flux<Profile> getAll() {
        return this.profileService.all();
    }

    @GetMapping("/{id}")
    Mono<Profile> getById(@PathVariable String id) {
        return this.profileService.get(id);
    }

    @PostMapping
    Mono<ResponseEntity<Profile>> create(@RequestBody Profile profile) {
        return this.profileService.create(profile.getEmail())
                .map(p -> ResponseEntity
                        .created(URI.create("/profile/" + profile.getId()))
                        .contentType(mediaType)
                        .build());
    }

    @DeleteMapping("/{id}")
    Mono<Profile> deleteById(@PathVariable String id) {
        return this.profileService.delete(id);
    }

    @PutMapping("/{id}")
    Mono<ResponseEntity<Profile>> put(@PathVariable String id, @RequestBody Profile profile) {
        return Mono.just(profile)
                .flatMap(p -> this.profileService.update(id, p.getEmail()))
                .map(p -> ResponseEntity
                        .ok()
                        .contentType(mediaType)
                        .build());

    }
}
