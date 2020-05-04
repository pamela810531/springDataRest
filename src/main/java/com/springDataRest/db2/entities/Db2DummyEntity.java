package com.springDataRest.db2.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dummy Entity: <br>
 * 為了方便使用jpaRepository，<br>
 * 繞過其必須給予Entity的要求，<br>
 * 以方便使用@Query，<br>
 * 故本Entity中無具意義的field。
 * 
 * @author Pamela
 *
 */
@Table
@Entity
public class Db2DummyEntity {

	@Id
	private int dummy_id;

}
