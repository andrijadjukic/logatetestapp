package useradmin.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import useradmin.domain.User;
import useradmin.repositories.JWTUserDetailsRepository;

@Service
public class ApplicationContextValues {

    private final JWTUserDetailsRepository jwtUserDetailsRepository;

    public ApplicationContextValues(JWTUserDetailsRepository jwtUserDetailsRepository) {
        this.jwtUserDetailsRepository = jwtUserDetailsRepository;
    }

    public Long getCurrentUserID() {
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            try {
                currentUserName = authentication.getName();
            }
            catch(Exception e) {
                currentUserName = "adjukic"; // za potrebe testa tj inicijalizacije podataka podeseno na adjukic ako je authentication.getName() prazno
            }
        }
        System.out.println("Username: " + currentUserName);
        Long currentUserID = getUserIdByUsername(currentUserName);
        if (currentUserID == 0L){
            currentUserID = 1L; // za potrebe testa tj inicijalizacije podataka podeseno na 1L
        }
        System.out.println("UserID: " + currentUserID);
        return currentUserID;
    }

    // funkcija koja se poziva u svakom save-u za dobijanje ID-ja korisnika koji vrsi izmjene
    public Long getUserIdByUsername(String username) {
        Long userId;
        User user = jwtUserDetailsRepository.findByUsername(username)
                .orElse(null);
        if (null == user) {
            userId = 0L;
        } else {
            userId = user.getId();
        }
        return userId;
    }
}
