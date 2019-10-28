package useradmin.api.v1.model;

import lombok.Data;


@Data
public class RoleDTO {
    private Long id;
    private String name;
    private String description;
    private boolean isActive;
}
