package com.example.firstapp.api.dto;
import com.example.firstapp.entity.User;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserResponseDto {
    Long id;
    String name;

    // Entityから変換する静的メソッド（ファクトリメソッド）
    public static UserResponseDto from(User user) {
        return new UserResponseDto(
            user.getId(),
            user.getName()
        );
    }
}