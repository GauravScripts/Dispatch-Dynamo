package com.example.authentication.Repository;

import com.example.authentication.Domain.Authentication;
import com.example.authentication.Repositery.AuthRepositery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AuthRepositeryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthRepositery authRepositery;

    @Test
    public void givenEmail_whenFindById_thenReturnAuthentication() {
        // given
        String email = "example@gmail.com";
        String password = "password123";
        Authentication auth = new Authentication(email, password, "User");
        entityManager.persistAndFlush(auth);

        // when
        Optional<Authentication> found = authRepositery.findById(email);

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getEmailId()).isEqualTo(email);
        assertThat(found.get().getPassword()).isEqualTo(password);
        assertThat(found.get().getRole()).isEqualTo("User");
    }

    @Test
    public void givenNonexistentEmail_whenFindById_thenReturnEmpty() {
        // given
        String email = "nonexistent@gmail.com";

        // when
        Optional<Authentication> found = authRepositery.findById(email);

        // then
        assertThat(found.isPresent()).isFalse();
    }
}
