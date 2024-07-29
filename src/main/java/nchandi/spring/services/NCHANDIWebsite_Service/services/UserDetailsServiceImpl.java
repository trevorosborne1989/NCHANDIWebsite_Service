package nchandi.spring.services.NCHANDIWebsite_Service.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PeopleRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final PeopleRepository peopleRepository;

  public UserDetailsServiceImpl(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<People> people = peopleRepository.findByUsername(username);
    if (people.size() <= 0) {
      throw new UsernameNotFoundException("Username %s does not exist".formatted(username));
    }
    People person = people.get(0);
    return new User(person.getUsername(), person.getPassword(), getAuthorities(person));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(People person) {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + person.getCommitment()));
  }
}
