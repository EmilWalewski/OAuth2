package security.SecurityConfigurations.endPoints;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpringSecuredEndPoint {

    @GetMapping("/me")
    @Scheduled(cron = "* * * * * *")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> endpoint1(){

        return ResponseEntity.ok("bolek");
    }

}
