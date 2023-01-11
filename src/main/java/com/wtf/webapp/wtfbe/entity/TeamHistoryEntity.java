package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.TeamHistoryDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "team_history")
public class TeamHistoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;

    private String history;

    public TeamHistoryDto convertToDto() {
        return TeamHistoryDto.builder()
                .id(this.id)
                .year(this.year)
                .history(this.history)
                .build();
    }
}
