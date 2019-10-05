package com.sample.build.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Build {
	private @Id @GeneratedValue int id;
	
	private String name;
	
	private String gitAddress;
	
	private int buildCount;
	
	private Date lastBuildDate;
	
	private Date createdBuildDate;
	
}
