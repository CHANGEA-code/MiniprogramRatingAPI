package com.datealive.entity.dto;


import com.datealive.entity.pojo.Team;
import com.datealive.entity.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamUser {
    private User captain;

    private List<User> teammate;

    private Team team;

}
