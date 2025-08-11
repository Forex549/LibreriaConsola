package com.example.Libreria.DTO;

import java.util.List;

public record RespuestaJsonDTO(
        Integer count,
        String next,
        String previous,
        List<LibroDTO> results
) {
}
