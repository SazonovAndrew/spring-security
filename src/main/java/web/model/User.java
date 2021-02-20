package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private int id;

   @Column(name = "name")
   private String name;

   @Column(name = "surname")
   private String surname;

   @Column(name = "city")
   private String city;

   @Column(name = "age")
   private int age;
//   @Size(min=2, message = "Не меньше 5 знаков")
   @Column(name = "username")
   private String username;
//   @Size(min=2, message = "Не меньше 5 знаков")
   @Column(name = "password")
   private String password;
   @Transient
   private String passwordConfirm;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {
   }

   public User(int id, String name, String surname, String city, int age, String username, String password) {
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.city = city;
      this.age = age;
      this.username = username;
      this.password = password;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + name + '\'' +
              ", lastName='" + surname + '\'' +
              ", email='" + city + '\'' +
              ", age=" + age +
              '}';
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }
}
