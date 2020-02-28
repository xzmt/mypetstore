package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOimpl implements AccountDAO {
    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT SIGNON.USERNAME,  ACCOUNT.EMAIL,  ACCOUNT.FIRSTNAME,  ACCOUNT.LASTNAME,  ACCOUNT.STATUS,  ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2,  ACCOUNT.CITY,  ACCOUNT.STATE,  ACCOUNT.ZIP,  ACCOUNT.COUNTRY,  ACCOUNT.PHONE,  PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption,  PROFILE.BANNEROPT AS bannerOption,  BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID  AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT  SIGNON.USERNAME,  ACCOUNT.EMAIL,  ACCOUNT.FIRSTNAME,  ACCOUNT.LASTNAME,  ACCOUNT.STATUS,  ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, STATUS = ?, ADDR1 = ?, ADDR2 = ?, CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, PHONE = ? WHERE USERID = ?";
    private static final String INSERT_ACCOUNT = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PROFILE = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?, MYLISTOPT = ?, BANNEROPT = ? WHERE USERID = ?";
    private static final String UPDATE_PROFILE2 = "UPDATE PROFILE SET LANGPREF = #{languagePreference}, FAVCATEGORY = #{favouriteCategoryId} WHERE USERID = #{username}";
    private static final String INSERT_PROFILE = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, MYLISTOPT, BANNEROPT, USERID) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_PROFILE2 = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (#{languagePreference}, #{favouriteCategoryId}, #{username})";
    private static final String UPDATE_SIGNON = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
    private static final String INSERT_SIGNON = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";
    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {

                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setCountry(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));

            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account2 = null;
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {

                account2 = new Account();
                account2.setUsername(resultSet.getString(1));
                account2.setCountry(resultSet.getString(2));
                account2.setFirstName(resultSet.getString(3));
                account2.setLastName(resultSet.getString(4));
                account2.setStatus(resultSet.getString(5));
                account2.setAddress1(resultSet.getString(6));
                account2.setAddress2(resultSet.getString(7));
                account2.setCity(resultSet.getString(8));
                account2.setState(resultSet.getString(9));
                account2.setZip(resultSet.getString(10));
                account2.setCountry(resultSet.getString(11));
                account2.setPhone(resultSet.getString(12));
                account2.setLanguagePreference(resultSet.getString(13));
                account2.setFavouriteCategoryId(resultSet.getString(14));
                account2.setListOption(resultSet.getBoolean(15));
                account2.setBannerOption(resultSet.getBoolean(16));
                account2.setBannerName(resultSet.getString(17));

            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return account2;
    }

    @Override
    public void insertAccount(Account account) {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());//可能会有问题
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setBoolean(3,account.isListOption());
            preparedStatement.setBoolean(4,account.isBannerOption());
            preparedStatement.setString(5,account.getUsername());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());//可能会有问题
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setBoolean(3,account.isListOption());
            preparedStatement.setBoolean(4,account.isBannerOption());
            preparedStatement.setString(5,account.getUsername());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
