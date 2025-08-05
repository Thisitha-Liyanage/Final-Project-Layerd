package lk.ijse.the_thirsty_manager.Model;

import javafx.scene.control.Alert;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.OrderDto;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Dto.TM.OrderTM;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderPageModel {

    public boolean placeOrder(OrderDto orderDto) throws SQLException {
        Connection connection = null;

        //save order
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String saveOrderSql = "insert into orders (order_id, customer_id, total_amount, table_id, date) values (?, ?, ?, ?, ?)";
            PreparedStatement saveOrderPstm = connection.prepareStatement(saveOrderSql);
            saveOrderPstm.setString(1, orderDto.getOrderID());
            saveOrderPstm.setString(2, orderDto.getCustomerID());
            saveOrderPstm.setDouble(3, orderDto.getTotalAmount());
            saveOrderPstm.setString(4, orderDto.getTableID());
            saveOrderPstm.setDate(5, Date.valueOf(LocalDate.now()));
            int affectedRows = saveOrderPstm.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback();
                return false;
            }

            //save order details
            String saveOrderDetailSql = "insert into oder_details ( order_id, item_id, quantity) values (  ? , ? , ?))";
            PreparedStatement saveOrderDetailPstm = connection.prepareStatement(saveOrderDetailSql);
            
            saveOrderDetailPstm.setString(1 , orderDto.getOrderID());
            saveOrderDetailPstm.setString(2 , orderDto.getItemID());
            saveOrderDetailPstm.setDouble(3 , orderDto.getQuantity());

            int detailAffectedRows = saveOrderDetailPstm.executeUpdate();
            if (detailAffectedRows == 0) {
                connection.rollback();
                return false;
            }

            //change quantity
            String updateStockSql = "UPDATE inventory i\n" +
                    "JOIN ingredients ing ON i.inventory_id = ing.inventory_id\n" +
                    "SET i.current_stock = i.current_stock - (ing.quantity_used * ?)\n" +
                    "WHERE ing.menu_item_id = ? ";
            PreparedStatement updateStockPstm = connection.prepareStatement(updateStockSql);

                saveOrderDetailPstm.setDouble(1, orderDto.getQuantity());
                saveOrderDetailPstm.setString(2, orderDto.getItemID());

                int updatequnatityDetail = saveOrderDetailPstm.executeUpdate();
                if (updatequnatityDetail == 0) {
                    connection.rollback();
                    return false;
                }

                updateStockPstm.setDouble(1, orderDto.getQuantity());
                updateStockPstm.setString(2, orderDto.getItemID());

                int stockAffectedRows = updateStockPstm.executeUpdate();
                if (stockAffectedRows == 0) {
                    connection.rollback();
                    return false;
                }

            connection.commit();
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select order_id from orders order by order_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    public ItemDto findItem(String ItemID)throws SQLException{

            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String foundItem = "select menu_items.name , menu_items.availability , price from menu_items where item_id = ?";
            PreparedStatement stm = connection.prepareStatement(foundItem);
            stm.setString(1 , ItemID);
            ResultSet resultSet = stm.executeQuery();

            ItemDto itemDto = new ItemDto();
            if(resultSet.next()){
                itemDto.setItemName(resultSet.getString(1));
                itemDto.setAvailability(resultSet.getString(2));
                itemDto.setPrice(resultSet.getDouble(3));
                return itemDto;
            }

        return null;
    }

    public boolean findCustomer(String cusID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select customer.customer_id from customer where customer_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 , cusID);

        ResultSet resultSet = stm.executeQuery();

        String findCusID =null;
        if(resultSet.next()){
            findCusID = resultSet.getString(1);
            return findCusID != null & cusID.equals(findCusID);
        }
        return false;
    }

    private ArrayList<OrderDto> cart = new ArrayList<>();
    public ArrayList<OrderDto> addToCart(OrderDto addcartOrderDto) throws SQLException {
        if(cart != null){
            cart.add(new OrderDto(addcartOrderDto.getCustomerID(), addcartOrderDto.getUnitPrice(), addcartOrderDto.getItemName(), (addcartOrderDto.getQuantity()), addcartOrderDto.getItemID()));
        }

        return cart;
    }
}
