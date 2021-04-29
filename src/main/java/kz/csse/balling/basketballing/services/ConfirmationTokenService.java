package kz.csse.balling.basketballing.services;



import kz.csse.balling.basketballing.entites.token.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    public void saveConfirmationToken(ConfirmationToken token);
    public Optional<ConfirmationToken> getToken(String token);
    public int setConfirmedAt(String token);
}
