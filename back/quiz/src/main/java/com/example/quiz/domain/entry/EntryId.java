package com.example.quiz.domain.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntryId implements Serializable {
    private long room;
    private long user;
}
