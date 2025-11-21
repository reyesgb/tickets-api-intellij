package cl.duocuc.psreyes.ticketsapii.ticket.repository;

import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketStatusCount;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketReportJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<TicketStatusCount> getCountByStatus() {
        String sql = """
                SELECT status, COUNT(*) AS count
                FROM tickets
                WHERE deleted = false
                GROUP BY status
        """;
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new TicketStatusCount(
                                rs.getString("status"),
                                rs.getLong("count")
                        )
        );
    }
}