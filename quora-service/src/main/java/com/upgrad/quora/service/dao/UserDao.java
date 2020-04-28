package com.upgrad.quora.service.dao;


import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * DAO class for providing user related database trasactions.
 */
@Repository
public class UserDao {

    //Constants
    private static final String USER_BY_EMAIL = "userByEmail";
    private static final String USER_AUTH_TOKEN_BY_ACCESS_TOKEN = "userAuthTokenByAccessToken";
    private static final String USER_BY_UUID = "userByUuid";
    private static final String USER_BY_USER_NAME = "userByUserName";
    private static final String DELETE_AUTH_TOKEN_BY_UUID = "deleteAuthTokenByUuid";
    private static final String CHECK_AUTH_TOKEN = "checkAuthToken";

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * This method is used for creating a new user.
     *
     * @param userEntity User object
     * @return created user Object
     */
    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    /**
     * This method is used for checking whether the userName used for
     * signup is already taken. If yes, then it will throw SignUpRestrictedException
     *
     * @param username userName used for signUp
     * @return boolean indicating whether the userName exists or not.
     */
    public UserEntity checkUserName(final String username) {
        try {
            return entityManager.createNamedQuery(USER_BY_USER_NAME, UserEntity.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }


    /**
     * This method is used for checking whether the emailId used for
     * signup is already taken. If yes, then it will throw SignUpRestrictedException
     *
     * @param emailid userName used for signUp
     * @return boolean indicating whether the userName exists or not.
     */
    public UserEntity checkEmailid(String emailid) {
        try {
            return entityManager.createNamedQuery(USER_BY_EMAIL, UserEntity.class).setParameter("email", emailid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }


    public UserAuthEntity getUserAuthToken(final String authorizationToken) {
        try {
            return entityManager
                    .createNamedQuery(USER_AUTH_TOKEN_BY_ACCESS_TOKEN, UserAuthEntity.class).setParameter("accessToken", authorizationToken)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * method to get user Entity by uuid.
     *
     * @param userUUid uuid of the user
     * @return UserEntity object
     */
    public UserEntity getUser(String userUUid) {
        try {
            return entityManager.createNamedQuery(USER_BY_UUID, UserEntity.class).setParameter("uuid", userUUid).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * method to get the user by username
     *
     * @param username username as String value
     * @return UserEntity Object
     */
    public UserEntity getUserByUserName(String username) {
        try {
            return entityManager.createNamedQuery(USER_BY_USER_NAME, UserEntity.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * method used for creating the authtoken and setting it in the database table user_auth
     *
     * @param userAuthTokenEntity userAuthtoken entity to be created.
     */
    public UserAuthEntity createAuthToken(final UserAuthEntity userAuthTokenEntity) {
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }

    /**
     * Method used for getting user by it's access token.
     * If accesstoken is not available in database then it returns null.
     *
     * @param accessToken String indicating the accessToken
     * @return UserAuthEntity
     */
    public UserAuthEntity getUserByAccessToken(String accessToken) {
        try {
            return entityManager.createNamedQuery(USER_AUTH_TOKEN_BY_ACCESS_TOKEN, UserAuthEntity.class).setParameter("accessToken", accessToken)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Method used for updating the logoutAt date of USerAuth table.
     * Once, user is successfully logged out, we will update the logoutAt date.
     *
     * @param userAuthEntity the userAuthEntity object which needs updation
     */
    public void updateLogOutDate(UserAuthEntity userAuthEntity) {
        entityManager.persist(userAuthEntity);
    }

    /**
     * Method used for deletion of user from database.
     *
     * @param user User to be deleted.
     */
    public void deleteUser(UserEntity user) {
        entityManager.remove(user);
    }

    /**
     * Method used for deleting exiting auth tokens for the user.
     */
    public void deleteExistingAuthDetailsForUser(String uuid) {
        try {
            entityManager.createNamedQuery(DELETE_AUTH_TOKEN_BY_UUID, UserAuthEntity.class).setParameter("uuid", uuid);
        } catch (Exception e) {
            return;
        }
    }
 /**
     * method used to check if the auth token is valid.
     
     */
    public UserAuthEntity checkAuthToken(final String authorizationToken) {
        try {

            return entityManager.createNamedQuery(CHECK_AUTH_TOKEN, UserAuthEntity.class).setParameter("accessToken", authorizationToken)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
