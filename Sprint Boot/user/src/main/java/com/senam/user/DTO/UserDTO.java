package com.senam.user.DTO;

import java.util.UUID;

public record UserDTO(UUID uuid, String username, String email) {
}
