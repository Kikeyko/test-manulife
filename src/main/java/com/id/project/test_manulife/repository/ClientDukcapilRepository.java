package com.id.project.test_manulife.repository;

import com.id.project.test_manulife.model.raw.dukcapil.ClientDukcapilRaw;
import com.id.project.test_manulife.mapper.ClientDukcapilRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientDukcapilRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ClientDukcapilRowMapper clientDukcapilRowMapper;

    public List<ClientDukcapilRaw> executeGetClientDukcapilDetails(Long policyNumber) {
        String sql = "SELECT * FROM public.get_client_dukcapil_details_by_policy_number(?)";
        return jdbcTemplate.query(sql, clientDukcapilRowMapper, policyNumber);
    }
}
