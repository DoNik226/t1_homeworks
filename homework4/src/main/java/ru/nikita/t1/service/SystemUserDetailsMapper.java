package ru.nikita.t1.service;

import ru.nikita.t1.model.SystemUserDetails;
import ru.nikita.t1.model.UserRole;
import ru.nikita.t1.model.dto.RegisterRequest;

import java.util.Set;

public class SystemUserDetailsMapper {

    public static SystemUserDetails createUserFromRegisterRequest(RegisterRequest userDto) {
        SystemUserDetails user = new SystemUserDetails();

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(Set.of(UserRole.GUEST));

        return user;
    }
}
