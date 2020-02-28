package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.ItemDAO;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.persistence.OrderDAO;
import org.csu.mypetstore.persistence.SequenceDAO;
import org.csu.mypetstore.persistence.impl.ItemDAOimpl;
import org.csu.mypetstore.persistence.impl.LineItemDAOimpl;
import org.csu.mypetstore.persistence.impl.OrderDAOimpl;
import org.csu.mypetstore.persistence.impl.SequenceDAOimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class OrderService {

  private ItemDAO itemDAO;
  private OrderDAO orderDAO;
  private SequenceDAO sequenceDAO;
  private LineItemDAO lineItemDAO;

  public OrderService() {
    itemDAO = new ItemDAOimpl();
    orderDAO = new OrderDAOimpl();
    sequenceDAO = new SequenceDAOimpl();
    lineItemDAO = new LineItemDAOimpl();
  }

  public void insertOrder(Order order) throws Exception{
    order.setOrderId(getNextId("ordernum"));
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      String itemId = lineItem.getItemId();
      Integer increment = lineItem.getQuantity();
      Map<String, Object> param = new HashMap<String, Object>(2);
      param.put("itemId", itemId);
      param.put("increment", increment);
      itemDAO.updateInventoryQuantity(param);
    }
    orderDAO.insertOrder(order);
    orderDAO.insertOrderStatus(order);
    System.out.println("111"+order.getUsername());
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      lineItemDAO.insertLineItem(lineItem);
    }
  }

  public Order getOrder(int orderId) {
    Order order = orderDAO.getOrder(orderId);
    order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));

    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      Item item = itemDAO.getItem(lineItem.getItemId());
      item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
      lineItem.setItem(item);
    }

    return order;
  }

  public List<Order> getOrdersByUsername(String username) {
    return orderDAO.getOrdersByUsername(username);
  }

  public int getNextId(String name) throws Exception{
    Sequence sequence = new Sequence(name, -1);
    sequence = (Sequence) sequenceDAO.getSequence(sequence);
    if (sequence == null) {
      throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
          + " sequence).");
    }
    Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
    sequenceDAO.updateSequence(parameterObject);
    return sequence.getNextId();
  }

}
