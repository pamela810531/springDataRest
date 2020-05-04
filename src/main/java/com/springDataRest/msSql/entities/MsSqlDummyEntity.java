package com.springDataRest.msSql.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MsSqlDummyEntity {
	@Id
	private int dummy_id;
}
