package nchandi.spring.services.NCHANDIWebsite_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {

  List<PasswordResetToken> findByToken(String token);

  List<PasswordResetToken> findByPersonId(String personId);

  @Transactional
  void deleteByPersonId(String personId);
}
