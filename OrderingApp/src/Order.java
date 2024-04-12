import java.time.LocalDate;
import java.util.Date;

/**
 * @author hmh
 * @date 2024/4/6
 * @desc 订单属性
 */
public class Order {

    public Order() {

    }

    public Order(int no,String subscriber, String dishesAndCount,
                 int date, String address, String status, double totalAmount) {
        this.no = no;
        this.subscriber = subscriber;
        this.dishesAndCount = dishesAndCount;
        this.date = date;
        this.address = address;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Order(int no,String dishName, double unitPrice, int likeNum) {
        this.no = no;
        this.dishName = dishName;
        this.unitPrice = unitPrice;
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return String.format("%-5d%-6s\t%-3.2f\t%-3d\n",
                no, dishName, unitPrice, likeNum);
    }

    public String toString1(){
        return String.format("%-5d%-3s\t%-10s\t%-7s\t%-6s\t%-3s\t\t%-3.2f\n",
                no,subscriber,dishesAndCount,date,address,status,totalAmount);
    }
    // 订单属性
    int no;
    String subscriber;// 订餐人姓名
    String dishesAndCount;// 菜品及份数
    int date ;// 订餐时间
    String address;// 订餐地址
    String status;// 订单状态
    double totalAmount;// 总金额


    // 菜单属性
    String dishName;// 菜名
    double unitPrice;// 单价
    int likeNum; // 点赞数量

}
