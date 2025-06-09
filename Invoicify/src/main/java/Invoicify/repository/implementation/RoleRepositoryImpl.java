package Invoicify.repository.implementation;

import static Invoicify.query.RoleQuery.INSERT_ROLE_ID_USER_QUERY;
import static Invoicify.query.RoleQuery.SELECT_ROLE_BY_NAME_QUERY;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import Invoicify.Exception.ApiException;
import Invoicify.RowMapper.RoleRowMapper;
import Invoicify.entity.Role;
import Invoicify.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j

public class RoleRepositoryImpl implements RoleRepository<Role>{
	
	private final NamedParameterJdbcTemplate jdbc;

	@Override
	public Role create(Role data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Role> list(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role update(Role data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoleToUser(long userId, String roleName) {
	    log.info("Attempting to add role '{}' to user ID {}", roleName, userId);
	    try {
	        Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("name", roleName), new RoleRowMapper());
	        jdbc.update(INSERT_ROLE_ID_USER_QUERY, Map.of("userId", userId, "roleId", requireNonNull(role).getId()));
	    } catch (EmptyResultDataAccessException ex) {
	        log.error("No role found with name '{}'", roleName);
	        throw new ApiException("Role not found: " + roleName);
	    } catch (Exception e) {
	        log.error("Error assigning role: {}", e.getMessage(), e);
	        throw new ApiException("An error occurred. Please try again.");
	    }

	}

	

	@Override
	public Role getRoleByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserRole(long userId, String roleName) {
		// TODO Auto-generated method stub
		
	}

}
