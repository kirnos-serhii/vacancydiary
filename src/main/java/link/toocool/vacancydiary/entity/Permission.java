package link.toocool.vacancydiary.entity;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    SELF_USERS_READ("self_user:read"),
    SELF_USERS_WRITE("self_user:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
