package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOimpl implements LineItemDAO {
    private static final String GET_LINEITEMS_BY_ORDERID = "SELECT ORDERID,  LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String INSERT_LINRITEM = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?,?, ?, ?)";
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> LineItemList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINEITEMS_BY_ORDERID);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                LineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch(Exception e) {

        }
        return LineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try
        {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINRITEM);
            preparedStatement.setInt(1,lineItem.getOrderId());
            preparedStatement.setInt(2,lineItem.getLineNumber());
            preparedStatement.setString(3,  lineItem.getItemId());
            preparedStatement.setInt(4,lineItem.getQuantity());
            preparedStatement.setBigDecimal(5,lineItem.getUnitPrice());

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
