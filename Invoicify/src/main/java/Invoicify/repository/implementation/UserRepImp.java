package Invoicify.repository.implementation;

import static Invoicify.Enumaration.RoleType.ROLE_USER;
import static Invoicify.Enumaration.VerificationType.ACCOUNT;
import static Invoicify.query.UserQuery.COUNT_USER_EMAIL_QUERY;
import static Invoicify.query.UserQuery.INSERT_ACCOUNT_VERIFICATION_URL_QUERY;
import static Invoicify.query.UserQuery.INSERT_USER_QUERY;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Invoicify.Exception.ApiException;
import Invoicify.entity.Role;
import Invoicify.entity.User;
import Invoicify.repository.RoleRepository;
import Invoicify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepImp implements UserRepository<User>{
	
	private final NamedParameterJdbcTemplate jdbc;
	private final  RoleRepository<Role>roleRepository;
	private final BCryptPasswordEncoder encoder;
	
	

	


	@Override
	public User create(User user) {
	    if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) 
	        throw new ApiException("Email is already in use. Use another email.");
	    
	    try {
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource parameters = getSqlParameterSource(user);
	        jdbc.update(INSERT_USER_QUERY, parameters, holder);

	        user.setId(requireNonNull(holder.getKey()).longValue());

	        roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());
	        
	        String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());

	        jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY, Map.of("userId", user.getId(), "url", verificationUrl));

	        // TODO: Inject and use email service to send verification email
	        // emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);

	        user.setEnabled(false);
	        user.setNotLocked(true);

	        return user;
	    } 
//	    catch (EmptyResultDataAccessException e) {
//	        throw new ApiException("No Role Found By Name: " + ROLE_USER.name());
//	    } 
	    catch (Exception e) {
	    	log.error(e.getMessage(),e);
	      
	        throw new ApiException("An error occurred. Please try again.");
	    }
	}


	



	@Override
	public Collection<User> list(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Integer getEmailCount(String email) {
		
		return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email",email),Integer.class);
	}
	
	private SqlParameterSource getSqlParameterSource(User user) {
		
		return new MapSqlParameterSource()
				.addValue("firstName",user.getFirstName())
				.addValue("lastName",user.getLastName())
				.addValue("email",user.getEmail())
				.addValue("password",encoder.encode(user.getPassword()));
	}

	private String getVerificationUrl(String key,String type) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/"+type+"/"+key).toUriString();
		
	}


	@Override
	public void addRoleToUser(long userId, String roleName) {
		// TODO Auto-generated method stub
		
	}







	



	

}
