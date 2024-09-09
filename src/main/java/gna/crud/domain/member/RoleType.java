package gna.crud.domain.member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
    private final String value;
}
