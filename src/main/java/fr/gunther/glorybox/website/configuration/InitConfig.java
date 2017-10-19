package fr.gunther.glorybox.website.configuration;

import fr.gunther.glorybox.website.entity.Box;
import fr.gunther.glorybox.website.entity.User;
import fr.gunther.glorybox.website.repository.BoxRepository;
import fr.gunther.glorybox.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
public class InitConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoxRepository boxRepository;

    @PostConstruct
    public void initData() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");

        User user = new User();
        user.setEmail("gunthernic@eisti.eu");
        user.setActive(1);
        user.setPassword(hashedPassword);
        userRepository.save(user);

        Box box = new Box();
        box.setCreationDate(new Date());
        box.setName("Box Normal");
        box.setPrice(24.0F);
        boxRepository.save(box);

        Box box2 = new Box();
        box2.setCreationDate(new Date());
        box2.setName("Petite Box");
        box2.setPrice(12.5F);
        boxRepository.save(box2);
    }

}
