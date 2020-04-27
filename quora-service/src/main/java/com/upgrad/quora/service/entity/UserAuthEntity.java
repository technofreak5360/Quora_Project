package com.upgrad.quora.service.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Table(name = "USER_AUTH", schema = "public")
@NamedQueries({
        @NamedQuery(name = "userAuthTokenByAccessToken", query = "select ut from UserAuthEntity ut where ut.accessToken = :accessToken and ut.logoutAt is null"),
        @NamedQuery(name = "checkAuthToken", query = "select ut from UserAuthEntity ut where ut.accessToken = :accessToken"),
        @NamedQuery(name = "deleteAuthTokenByUuid", query = "delete  from UserAuthEntity ut where ut.uuid=:uuid")
})
public class UserAuthEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    //@OneToOne
    //@JoinColumn(name = "uuid", referencedColumnName = "uuid")
    @Column(name = "uuid")
    @NotNull
    @Size(max = 200)
    private String uuid;


    @ManyToOne
    @NotNull
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "access_token")
    @NotNull
    @Size(max = 500)
    private String accessToken;


    @Column(name = "LOGIN_AT")
    @NotNull
    private ZonedDateTime loginAt;

    @Column(name = "EXPIRES_AT")
    @NotNull
    private ZonedDateTime expiresAt;

    @Column(name = "LOGOUT_AT")
    private ZonedDateTime logoutAt;


    /**
     * accessor method for property id
     *
     * @return Integer id value
     */
    public Integer getId() {
        return id;
    }

    /**
     * accessor method for property uuid
     *
     * @return uuid of the user
     */
    public String getUuid() {
        return uuid;
    }


    /**
     * modifier method for property id
     *
     * @param uuid String uuid value
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * accessor method for property user
     *
     * @return UserEntity Object
     */
    public UserEntity getUser() {
        return user;
    }


    /**
     * modifier method for property id
     *
     * @param id String id value
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * modifier method for property user
     *
     * @param user Userentity object
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }


    /**
     * accessor method for property accesstoken
     *
     * @return String indicating access token
     */
    public String getAccessToken() {
        return accessToken;
    }


    /**
     * modifier method for property accesstoken
     *
     * @param accessToken user access token value
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * accessor method for property loginAt
     *
     * @return date indicating login at time
     */
    public ZonedDateTime getLoginAt() {
        return loginAt;
    }


    /**
     * modifier method for property loginAt
     *
     * @param loginAt date indicating loginat time
     */
    public void setLoginAt(ZonedDateTime loginAt) {
        this.loginAt = loginAt;
    }


    /**
     * accessor method for property expiresAt
     *
     * @return ExpiryAt date
     */
    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }


    /**
     * modifier method for property expiresAt
     *
     * @param expiresAt expiryAt date value
     */
    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * accessor method for property logoutAt
     *
     * @return LogoutAt date value
     */
    public ZonedDateTime getLogoutAt() {
        return logoutAt;
    }


    /**
     * modifier method for property logoutAt
     *
     * @param logoutAt logoutAt date value
     */
    public void setLogoutAt(ZonedDateTime logoutAt) {
        this.logoutAt = logoutAt;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
