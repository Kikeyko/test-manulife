package com.id.project.test_manulife.mapper;

import com.id.project.test_manulife.model.raw.dukcapil.ClientDukcapilRaw;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientDukcapilRowMapper implements RowMapper<ClientDukcapilRaw> {

    @Override
    public ClientDukcapilRaw mapRow(ResultSet rs, int rowNum) throws SQLException {
        Date birthDt = rs.getDate("birth_date");

        return ClientDukcapilRaw.builder()
                .policyNumber(rs.getLong("policy_number"))
                .linkType(rs.getString("link_type"))
                .clientNumber(rs.getLong("client_number"))
                .clientName(rs.getString("client_name"))
                .birthDate(birthDt != null ? birthDt.toLocalDate() : null)
                .identityNumber(rs.getString("identity_number"))
                .genderCode(rs.getString("gender_code"))
                .identityNumberResult(rs.getString("identity_number_result"))
                .clientNameResult(rs.getString("client_name_result"))
                .birthDateResult(rs.getString("birth_date_result"))
                .genderCodeResult(rs.getString("gender_code_result"))
                .summaryResult(rs.getString("summary_result"))
                .summaryCode(rs.getString("summary_code"))
                .resultCode(rs.getString("result_code"))
                .build();
    }
}
