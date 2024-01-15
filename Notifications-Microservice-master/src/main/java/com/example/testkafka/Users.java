package com.example.testkafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Long id;
    //@NotNull(message = "L'email est obligatoire !")
    private String email;
    //@NotNull(message = "Veuillez entrer votre nom svp")
    private String name;
    //@NotNull(message = "Le mot de passe est obligatoire !")
    private String password;
    //@NotNull(message = "Le groupe sanguin est obligatoire !")
    private String groupeSanguin;
    //@NotNull(message = "Votre age est obligatoire !")
    private Long age;
}