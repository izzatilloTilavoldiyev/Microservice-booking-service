package uz.pdp.bookingservice.entity;

import lombok.*;
import uz.pdp.bookingservice.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{

    private String name;

    private String email;

    private String password;

    private Role role;

    private String state;
}
