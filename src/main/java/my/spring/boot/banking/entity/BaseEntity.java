package my.spring.boot.banking.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8318738482625074969L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	protected Long id;

	@Column(name = "ACTIVE", nullable = false)
	@Basic(optional = false)
	protected Boolean active;

	@Column(name = "CREATED_BY", nullable = false)
	@Basic(optional = false)
	protected Long createdBy;

	@Column(name = "CREATED_ON", nullable = false)
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdOn;

	@Column(name = "MODIFIED_BY", nullable = true)
	@Basic(optional = true)
	protected Long modifiedBy;

	@Column(name = "MODIFIED_ON", nullable = true)
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [ID=" + id + "]";
	}
}