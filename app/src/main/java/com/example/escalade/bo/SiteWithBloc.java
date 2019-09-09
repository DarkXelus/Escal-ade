package com.example.escalade.bo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SiteWithBloc {
    @Embedded
    public Site site;

    @Relation(parentColumn = "id", entityColumn = "SiteId", entity = Bloc.class)
    public List<Bloc> blocs;
}
