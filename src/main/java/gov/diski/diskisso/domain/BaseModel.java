package gov.diski.diskisso.domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseModel {

	public BaseModel() {
		super();
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	private Long id;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate", updatable = false)
	@Getter
	@Setter
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	@Getter
	@Setter
	private Date updatedDate;

	@PrePersist
	protected void onCreate() {
		Date now = new Date();
		setCreatedDate(now);
	}

	@PreUpdate
	protected void onUpdate() {
		Date now = new Date();
		setUpdatedDate(now);
	}

}
