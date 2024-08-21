package com.devteria.identity.constant;

public enum PredefinedRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    PredefinedRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static PredefinedRole fromRoleName(String roleName) {
        for (PredefinedRole role : PredefinedRole.values()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant with roleName " + roleName);
    }
}
