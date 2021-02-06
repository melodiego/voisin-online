package br.com.voisinonline.config.security.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseProperties implements Serializable {
    private static final long serialVersionUID = -2809824144740738727L;

    private int sessionExpiryInDays;
    private String databaseUrl;
    private boolean enableStrictServerSession;
    private boolean enableCheckSessionRevoked;
    private boolean enableLogoutEverywhere;
}