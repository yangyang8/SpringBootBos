package com.hailong.bos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;



/**
 * 创建用户的pojo对象
 * @author Administrator
 *
 */
@Entity	//进行自动在数据库当中创建一个表
//@NamedQuery(name="user.updateByCondition", 
//query="update User set password=? where id=?")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="user",strategy="uuid")
	@GeneratedValue(generator="user")  //表示主键的生成策略
	private String id;  //表的唯一标识
	private String username;
	private String password;
	private Double salary;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	private String gender;
	private String station; //操作单位
	private String telephone;
	private String remark;
	
	
	@ManyToMany //这个值为我们的维护主键的那个值的集合的属性
	@JoinTable(name="role_user",joinColumns={@JoinColumn(name="user_id")},
	inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<TRole> roleSet=new HashSet<TRole>();
	
	//一个用户有多个
	
	//一个用户可以制定多个收派标准
/*	@JoinColumn(name="user_id")
	@OneToMany
	@JsonBackReference
	private List<BCStandard> standards=new ArrayList<BCStandard>();
	
	public List<BCStandard> getStandards() {
		return standards;
	}
	public void setStandards(List<BCStandard> standards) {
		this.standards = standards;
	}*/
	
	public String getRoleName(){
		if(roleSet!=null&&roleSet.size()>0){
			String RoleName="";
			for(TRole role:roleSet){
				RoleName+=role.getName()+" ";
			}
			return RoleName;
		}else{
			return "此用户没有角色关联数据";
		}
	}
	
	public String getId() {
		return id;
	}
	public Set<TRole> getRoleSet() {
		return roleSet;
	}
	public void setRoleSet(Set<TRole> roleSet) {
		this.roleSet = roleSet;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salary=" + salary
				+ ", birthday=" + birthday + ", gender=" + gender + ", station=" + station + ", telephone=" + telephone
				+ ", remark=" + remark + "]";
	}
	
}
